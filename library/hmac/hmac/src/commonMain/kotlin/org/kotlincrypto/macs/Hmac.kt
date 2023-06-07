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

import org.kotlincrypto.core.Digest
import org.kotlincrypto.core.InternalKotlinCryptoApi
import org.kotlincrypto.core.Mac
import kotlin.experimental.xor

/**
 * Core abstraction for Hash-based Message Authentication
 * Code implementations.
 *
 * @see [Mac]
 * @see [Digest]
 * */
public abstract class Hmac: Mac {

    /**
     * Primary constructor for creating a new [Hmac] instance
     *
     * @throws [IllegalArgumentException] if [key] is empty or [algorithm] is blank.
     * */
    @InternalKotlinCryptoApi
    @Throws(IllegalArgumentException::class)
    protected constructor(
        key: ByteArray,
        algorithm: String,
        digest: Digest,
    ): this(Hmac.Engine(key, algorithm, digest))

    /**
     * Secondary constructor for implementing [copy].
     *
     * Implementors of [Hmac] should have a private secondary constructor
     * that is utilized by its [copy] implementation.
     *
     * @throws [ClassCastException] if [engine] is not [Hmac.Engine]
     * */
    @InternalKotlinCryptoApi
    @Throws(ClassCastException::class)
    protected constructor(engine: Mac.Engine): super((engine as Hmac.Engine).algorithm, engine)

    private class Engine: Mac.Engine {

        val algorithm: String
        private val iKey: ByteArray
        private val oKey: ByteArray
        private val digest: Digest

        @OptIn(InternalKotlinCryptoApi::class)
        constructor(key: ByteArray, algorithm: String, digest: Digest): super(key) {
            this.algorithm = algorithm

            val sizedKey = if (key.size > digest.blockSize()) {
                val keyHash = digest.digest(key)
                keyHash.copyOf(digest.blockSize()).also { keyHash.fill(0) }
            } else {
                // Even if provided key is the correct size, still
                // create a copy so sizedKey can always be blanked
                // after deriving iKey and oKey.
                //
                // If the provided key is undersized, it will be
                // padded with 0's.
                key.copyOf(digest.blockSize())
            }

            this.iKey = ByteArray(digest.blockSize()) { i -> sizedKey[i] xor I_PAD }
            this.oKey = ByteArray(digest.blockSize()) { i -> sizedKey[i] xor O_PAD }

            sizedKey.fill(0)

            digest.update(iKey)
            this.digest = digest
        }

        @OptIn(InternalKotlinCryptoApi::class)
        private constructor(state: State, engine: Engine): super(state) {
            this.algorithm = engine.algorithm
            this.iKey = engine.iKey
            this.oKey = engine.oKey
            this.digest = engine.digest.copy()
        }

        override fun update(input: Byte) { digest.update(input) }
        override fun update(input: ByteArray, offset: Int, len: Int) { digest.update(input, offset, len) }

        override fun doFinal(): ByteArray {
            val iFinal = digest.digest()
            digest.update(oKey)
            return digest.digest(iFinal)
        }

        override fun reset() {
            digest.reset()
            digest.update(iKey)
        }

        override fun macLength(): Int = digest.digestLength()

        override fun copy(): Engine = Engine(object : State() {}, this)

        private companion object {
            private const val I_PAD: Byte = 0x36
            private const val O_PAD: Byte = 0x5C
        }
    }
}
