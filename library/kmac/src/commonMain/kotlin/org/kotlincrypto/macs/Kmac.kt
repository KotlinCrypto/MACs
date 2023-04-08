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
    ): this(Kmac.Engine(key, S, bitStrength))

    @OptIn(InternalKotlinCryptoApi::class)
    protected constructor(engine: Mac.Engine): super((engine as Kmac.Engine).algorithm(), engine) {
        this.engine = engine
    }

    @OptIn(InternalKotlinCryptoApi::class)
    public sealed class KMACXofFactory<A: Kmac>: XofFactory<A>() {
        protected inner class KMACXof(delegate: A): XofDelegate(delegate) {
            override fun newReader(delegateCopy: A): Reader {
                val cShakeReader = delegateCopy.engine.padAndProvideReader(isXofMode = true)

                return object : Xof<A>.Reader() {
                    override fun readProtected(out: ByteArray, offset: Int, len: Int, bytesRead: Long) {
                        cShakeReader.read(out, offset, len)
                    }

                    override fun closeProtected() {
                        cShakeReader.close()
                    }
                }
            }

            @Suppress("UNCHECKED_CAST")
            override fun copy(): Xof<A> = KMACXof(delegate.copy() as A)
        }
    }

    private class Engine: Mac.Engine, Algorithm {

        private val xof: Xof<*>
        private val bitStrength: Int
        private val blockSize: Int
        private val initBlock: ByteArray
        private val outputLength: Int

        // TODO: NIST.SP.800-185 states that key can be any length, including 0
        @OptIn(InternalKotlinCryptoApi::class)
        constructor(key: ByteArray, S: ByteArray?, bitStrength: Int): super(key) {
            this.bitStrength = bitStrength
            val N = KMAC.encodeToByteArray()

            when (bitStrength) {
                BIT_STRENGTH_128 -> {
                    this.xof = CSHAKE128.xOf(N, S)
                    this.blockSize = CSHAKE128.BLOCK_SIZE
                    this.outputLength = CSHAKE128.DIGEST_LENGTH
                }
                BIT_STRENGTH_256 -> {
                    this.xof = CSHAKE256.xOf(N, S)
                    this.blockSize = CSHAKE256.BLOCK_SIZE
                    this.outputLength = CSHAKE256.DIGEST_LENGTH
                }
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
            this.initBlock.bytepad()
        }

        @OptIn(InternalKotlinCryptoApi::class)
        private constructor(state: State, engine: Engine): super(state) {
            this.xof = engine.xof.copy()
            this.bitStrength = engine.bitStrength
            this.blockSize = engine.blockSize
            this.outputLength = engine.outputLength
            this.initBlock = engine.initBlock
        }

        override fun update(input: Byte) { xof.update(input) }
        override fun update(input: ByteArray) { xof.update(input) }
        override fun update(input: ByteArray, offset: Int, len: Int) { xof.update(input, offset, len) }

        override fun doFinal(): ByteArray {
            val out = ByteArray(macLength())
            padAndProvideReader(isXofMode = false).use { read(out) }
            return out
        }

        fun padAndProvideReader(isXofMode: Boolean): Xof<*>.Reader {
            // Depending on if KMAC is being utilized as a Xof or as a Mac,
            // the output byte length will either be the 0 or macLength(),
            // respectively.
            //
            // https://nvlpubs.nist.gov/nistpubs/SpecialPublications/NIST.SP.800-185.pdf#4.3.1%20KMAC%20with%20Arbitrary-Length%20Output
            val outputByteLength = if (isXofMode) 0 else macLength()

            @OptIn(InternalKotlinCryptoApi::class)
            val encL = Xof.Utils.rightEncode(outputByteLength * 8L)
            xof.update(encL)

            // If this is being used as a Mac, doFinal was called so no
            // need to reset unnecessarily here as Mac.doFinal will call
            // engine.reset() right after output is returned.
            //
            // If this is being used as a Xof, padAndProvideReader is
            // called from a copy and will be single use for that Reader,
            // so resetting it is needed before it gets black holed.
            return xof.reader(resetXof = isXofMode)
        }

        @OptIn(InternalKotlinCryptoApi::class)
        private fun ByteArray.bytepad() {
            update(this)

            val remainder = size % blockSize

            // No padding is needed
            if (remainder == 0) return

            repeat(blockSize - remainder) {
                update(0)
            }
        }

        override fun reset() {
            xof.reset()
            initBlock.bytepad()
        }

        override fun algorithm(): String = KMAC + bitStrength
        override fun macLength(): Int = outputLength

        override fun copy(): Engine = Engine(object : State() {}, this)

        private companion object {
            private const val KMAC = "KMAC"
        }
    }

    protected companion object {
        internal const val BIT_STRENGTH_128 = 128
        internal const val BIT_STRENGTH_256 = 256
    }
}
