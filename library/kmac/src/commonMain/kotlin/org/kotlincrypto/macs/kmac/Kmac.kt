/*
 * Copyright (c) 2023 Matthew Nelson
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
@file:Suppress("RemoveRedundantQualifierName", "SpellCheckingInspection", "LocalVariableName")

package org.kotlincrypto.macs.kmac

import org.kotlincrypto.core.*
import org.kotlincrypto.core.digest.Digest
import org.kotlincrypto.core.mac.Mac
import org.kotlincrypto.core.xof.*
import org.kotlincrypto.hash.sha3.CSHAKE128
import org.kotlincrypto.hash.sha3.CSHAKE256

/**
 * Core abstraction for Keccak-based Message Authentication
 *
 * Code implementations:
 *  - [KMAC128]
 *  - [KMAC256]
 * */
public sealed class Kmac: Mac, ReKeyableXofAlgorithm {

    private val engine: Engine

    protected constructor(
        key: ByteArray,
        S: ByteArray?,
        bitStrength: Int,
        outputLength: Int,
        xofMode: Boolean,
    ): this(if (xofMode) {
        XofEngine(key, S, bitStrength, outputLength)
    } else {
        DigestEngine(key, S, bitStrength, outputLength)
    })

    protected constructor(other: Kmac): this(other.engine.copy())

    private constructor(engine: Kmac.Engine): super(engine.algorithm(), engine) {
        this.engine = engine
    }

    public abstract override fun copy(): Kmac

    /**
     * Provides [Xof] functionality for [KMAC128] & [KMAC256]
     *
     * @see [KMAC128.Companion]
     * @see [KMAC256.Companion]
     * */
    public sealed class KMACXofFactory<A: Kmac>: XofFactory<A>() {

        protected inner class KMACXof(delegate: A): XofDelegate(delegate) {

            init {
                require(delegate.engine is XofEngine) { "delegate must use XofEngine" }
            }

            override fun newReader(delegateCopy: A): Reader {
                val reader = (delegateCopy.engine as XofEngine).reader()

                return object : Xof<A>.Reader() {
                    override fun readProtected(out: ByteArray, offset: Int, len: Int): Int {
                        return reader.read(out, offset, len)
                    }

                    override fun closeProtected() {
                        reader.close()
                    }
                }
            }

            @Suppress("UNCHECKED_CAST")
            override fun copy(): Xof<A> = KMACXof(delegate.copy() as A)
        }
    }

    private class DigestEngine: Engine {

        override val source: Digest

        constructor(
            key: ByteArray,
            S: ByteArray?,
            bitStrength: Int,
            outputLength: Int,
        ): super(key, bitStrength, outputLength) {
            val N = KMAC.encodeToByteArray()

            this.source = if (bitStrength == BIT_STRENGTH_128) {
                CSHAKE128(N, S, outputLength)
            } else {
                CSHAKE256(N, S, outputLength)
            }

            bytepad()
        }

        private constructor(other: DigestEngine): super(other) {
            this.source = other.source.copy()
        }

        override fun copy(): DigestEngine = DigestEngine(this)

        override fun doFinal(): ByteArray {
            padFinal()
            return source.digest()
        }
    }

    private class XofEngine: Engine {

        override val source: Xof<*>

        constructor(
            key: ByteArray,
            S: ByteArray?,
            bitStrength: Int,
            outputLength: Int,
        ): super(key, bitStrength, outputLength) {
            val N = KMAC.encodeToByteArray()

            this.source = if (bitStrength == BIT_STRENGTH_128) {
                CSHAKE128.xOf(N, S)
            } else {
                CSHAKE256.xOf(N, S)
            }

            bytepad()
        }

        private constructor(other: XofEngine): super(other) {
            this.source = other.source.copy()
        }

        override fun copy(): XofEngine = XofEngine(this)

        // Only ever called from a copy of the XofEngine.
        fun reader(): Xof<*>.Reader {
            padFinal()

            // Ensure key material gets wiped for this new copy. Will not
            // be reset or updated again (in reader mode).
            zeroOutInitBlock()

            // Calling source.reader
            // will also create a copy of the underlying CSHAKE XOF, so we want to reset
            // the underlying copy before it is black holed.
            return source.reader(resetXof = true)
        }

        // Never called
        override fun doFinal(): ByteArray = error("Xof Mode")
    }

    @OptIn(InternalKotlinCryptoApi::class)
    private sealed class Engine: Mac.Engine, Algorithm {

        protected abstract val source: Updatable
        private val bitStrength: Int
        private val blockSize: Int
        private var initBlock: ByteArray
        private val outputLength: Int

        constructor(key: ByteArray, bitStrength: Int, outputLength: Int): super(key) {
            this.bitStrength = bitStrength
            this.outputLength = outputLength

            this.blockSize = when (bitStrength) {
                BIT_STRENGTH_128 -> CSHAKE128.BLOCK_SIZE
                BIT_STRENGTH_256 -> CSHAKE256.BLOCK_SIZE
                else -> throw IllegalArgumentException("bitStrength must be $BIT_STRENGTH_128 or $BIT_STRENGTH_256")
            }

            this.initBlock = newInitBlock(key, this.blockSize)
        }

        constructor(other: Engine): super(other) {
            this.bitStrength = other.bitStrength
            this.blockSize = other.blockSize
            this.outputLength = other.outputLength
            this.initBlock = other.initBlock.copyOf()
        }

        abstract override fun copy(): Kmac.Engine

        protected fun bytepad() {
            source.update(initBlock)

            val remainder = initBlock.size % blockSize

            // No padding is needed
            if (remainder == 0) return

            val pad = blockSize - remainder
            source.update(ByteArray(pad), 0, pad)
        }

        protected fun padFinal() {
            // TODO: Use hi/lo bits
            val encL = Xof.Utils.rightEncode(outputLength * 8L)
            source.update(encL)
        }

        protected fun zeroOutInitBlock() { initBlock.fill(0) }

        final override fun update(input: Byte) { source.update(input) }
        final override fun update(input: ByteArray, offset: Int, len: Int) { source.update(input, offset, len) }
        final override fun reset() { (source as Resettable).reset(); bytepad() }
        final override fun reset(newKey: ByteArray) {
            val oldInitBlock = this.initBlock
            initBlock = newInitBlock(newKey, blockSize)
            reset()
            oldInitBlock.fill(0)
        }

        final override fun algorithm(): String = KMAC + bitStrength
        final override fun macLength(): Int = outputLength

        // This combines inputs for bytepad(encode_string(K), w Int)
        // into a single ByteArray, the initBlock.
        @Suppress("NOTHING_TO_INLINE", "KotlinRedundantDiagnosticSuppress")
        private inline fun newInitBlock(key: ByteArray, blockSize: Int): ByteArray {
            // TODO: Use hi/lo bits
            // encode_string(K) = (encoded key bit size) + (key)
            // this is the encoded key size needed for correctly sizing b
            val encKS = Xof.Utils.leftEncode(key.size * 8L)

            val encW = Xof.Utils.leftEncode(blockSize)

            val initBlock = ByteArray(encW.size + encKS.size + key.size)
            encW.copyInto(initBlock)
            encKS.copyInto(initBlock, encW.size)
            key.copyInto(initBlock, encW.size + encKS.size)

            return initBlock
        }

        protected companion object {
            internal const val KMAC = "KMAC"
        }
    }

    protected companion object {
        internal const val BIT_STRENGTH_128 = 128
        internal const val BIT_STRENGTH_256 = 256
    }
}
