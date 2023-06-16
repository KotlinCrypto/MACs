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
@file:Suppress("UnnecessaryOptInAnnotation", "RemoveRedundantQualifierName")

package org.kotlincrypto.macs

import org.kotlincrypto.core.*
import org.kotlincrypto.hash.sha3.CSHAKE128
import org.kotlincrypto.hash.sha3.CSHAKE256
import kotlin.jvm.JvmField

/**
 * Core abstraction for Keccak-based Message Authentication
 * Code implementations:
 *  - KMAC128
 *  - KMAC256
 *
 * @see [Mac]
 * */
public sealed class Kmac: Mac, XofAlgorithm {

    private val engine: Kmac.Engine

    @OptIn(InternalKotlinCryptoApi::class)
    protected constructor(
        key: ByteArray,
        S: ByteArray?,
        bitStrength: Int,
        outputLength: Int,
        xofMode: Boolean,
    ): this(if (xofMode) {
        XofEngine(key, S, bitStrength)
    } else {
        DigestEngine(key, S, bitStrength, outputLength)
    })

    @OptIn(InternalKotlinCryptoApi::class)
    protected constructor(engine: Mac.Engine): super((engine as Kmac.Engine).algorithm(), engine) {
        this.engine = engine
    }

    @OptIn(InternalKotlinCryptoApi::class)
    public sealed class KMACXofFactory<A: Kmac>: XofFactory<A>() {
        protected inner class KMACXof(delegate: A): XofDelegate(delegate) {

            init {
                require(delegate.engine is XofEngine) { "delegate must use XofEngine" }
            }

            override fun newReader(delegateCopy: A): Reader {
                val reader = (delegateCopy.engine as XofEngine).reader()

                return object : Xof<A>.Reader() {
                    override fun readProtected(out: ByteArray, offset: Int, len: Int, bytesRead: Long) {
                        reader.read(out, offset, len)
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

        protected override val source: Digest

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

        private constructor(state: State, engine: DigestEngine): super(state, engine) {
            this.source = engine.source.copy()
        }

        override fun copy(): Mac.Engine = DigestEngine(object : State() {}, this)

        override fun doFinal(): ByteArray {
            padFinal()
            return source.digest()
        }
    }

    private class XofEngine: Engine {

        protected override val source: Xof<*>

        constructor(
            key: ByteArray,
            S: ByteArray?,
            bitStrength: Int,
        ): super(key, bitStrength, outputLength = 0) {
            val N = KMAC.encodeToByteArray()

            this.source = if (bitStrength == BIT_STRENGTH_128) {
                CSHAKE128.xOf(N, S)
            } else {
                CSHAKE256.xOf(N, S)
            }

            bytepad()
        }

        private constructor(state: State, engine: XofEngine): super(state, engine) {
            this.source = engine.source.copy()
        }

        override fun copy(): Mac.Engine = XofEngine(object: State() {}, this)

        // Only ever called from a copy of the XofEngine. Calling source.reader
        // will also create a copy of the underlying CSHAKE XOF, so we want to reset
        // the underlying copy before it is black holed.
        fun reader(): Xof<*>.Reader {
            padFinal()
            return source.reader(resetXof = true)
        }

        // Never called
        override fun doFinal(): ByteArray = ByteArray(0)
    }

    private sealed class Engine: Mac.Engine, Algorithm {

        protected abstract val source: Updatable
        private val bitStrength: Int
        private val blockSize: Int
        private val initBlock: ByteArray
        private val outputLength: Int

        @OptIn(InternalKotlinCryptoApi::class)
        constructor(key: ByteArray, bitStrength: Int, outputLength: Int): super(key) {
            this.bitStrength = bitStrength
            this.outputLength = outputLength

            this.blockSize = when (bitStrength) {
                BIT_STRENGTH_128 -> CSHAKE128.BLOCK_SIZE
                BIT_STRENGTH_256 -> CSHAKE256.BLOCK_SIZE
                else -> throw IllegalArgumentException("bitStrength must be $BIT_STRENGTH_128 or $BIT_STRENGTH_256")
            }

            // This combines inputs for bytepad(encode_string(K), w Int)
            // into a single ByteArray, the initBlock.
            val encW = Xof.Utils.leftEncode(blockSize.toLong())

            // encode_string(K) = (encoded key bit size) + (key)
            // this is the encoded key size needed for correctly sizing b
            val encKS = Xof.Utils.leftEncode(key.size * 8L)

            val b = ByteArray(encW.size + encKS.size + key.size)
            encW.copyInto(b)
            encKS.copyInto(b, encW.size)
            key.copyInto(b, encW.size + encKS.size)

            this.initBlock = b
        }

        @OptIn(InternalKotlinCryptoApi::class)
        constructor(state: State, engine: Engine): super(state) {
            this.bitStrength = engine.bitStrength
            this.blockSize = engine.blockSize
            this.outputLength = engine.outputLength
            this.initBlock = engine.initBlock
        }

        protected fun bytepad() {
            source.update(initBlock)

            val remainder = initBlock.size % blockSize

            // No padding is needed
            if (remainder == 0) return

            repeat(blockSize - remainder) {
                source.update(0)
            }
        }

        protected fun padFinal() {
            @OptIn(InternalKotlinCryptoApi::class)
            val encL = Xof.Utils.rightEncode(outputLength * 8L)
            source.update(encL)
        }

        final override fun update(input: Byte) { source.update(input) }
        final override fun update(input: ByteArray, offset: Int, len: Int) { source.update(input, offset, len) }
        final override fun reset() { (source as Resettable).reset(); bytepad() }

        final override fun algorithm(): String = KMAC + bitStrength
        final override fun macLength(): Int = outputLength

        protected companion object {
            internal const val KMAC = "KMAC"
        }
    }

    protected companion object {
        internal const val BIT_STRENGTH_128 = 128
        internal const val BIT_STRENGTH_256 = 256
    }
}
