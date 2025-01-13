/*
 * Copyright (c) 2025 Matthew Nelson
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
package org.kotlincrypto.macs.blake2

import org.kotlincrypto.core.Algorithm
import org.kotlincrypto.core.InternalKotlinCryptoApi
import org.kotlincrypto.core.mac.Mac
import org.kotlincrypto.hash.blake2.BLAKE2Digest

/**
 * Core abstraction for BLAKE2 Keyed Hashing in the form of Message Authentication
 *
 * Code implementations:
 *  - [BLAKE2b]
 *  - [BLAKE2s]
 * */
public sealed class BLAKE2Mac: Mac {

    @Throws(IllegalArgumentException::class)
    protected constructor(
        key: ByteArray,
        bitStrength: Int,
        personalization: ByteArray?,
        factory: BLAKE2Digest.KeyedHashFactory<*>,
    ): this(Engine(key, bitStrength, personalization, factory))

    protected constructor(other: BLAKE2Mac): super(other)

    private constructor(engine: Engine): super(engine.algorithm(), engine)

    public abstract override fun copy(): BLAKE2Mac

    @OptIn(InternalKotlinCryptoApi::class)
    private class Engine: Mac.Engine, Algorithm {

        private val bitStrength: Int
        private val keyBlock: ByteArray
        private val personalization: ByteArray?
        private val factory: BLAKE2Digest.KeyedHashFactory<*>
        private var digest: BLAKE2Digest

        @Throws(IllegalArgumentException::class)
        constructor(
            key: ByteArray,
            bitStrength: Int,
            personalization: ByteArray?,
            factory: BLAKE2Digest.KeyedHashFactory<*>,
        ): super(key) {
            this.bitStrength = bitStrength
            this.personalization = personalization?.copyOf()
            this.digest = factory.keyedHashInstance(
                bitStrength = bitStrength,
                keyLength = key.size,
                salt = null,
                personalization = this.personalization,
            )
            this.factory = factory
            this.keyBlock = key.copyOf(this.digest.blockSize())
            this.digest.update(this.keyBlock)
        }

        private constructor(other: Engine): super(other) {
            this.bitStrength = other.bitStrength
            this.keyBlock = other.keyBlock.copyOf()
            this.personalization = other.personalization
            this.factory = other.factory
            this.digest = other.digest.copy()
        }

        override fun copy(): Engine = Engine(this)

        override fun update(input: Byte) { digest.update(input) }

        override fun update(input: ByteArray, offset: Int, len: Int) { digest.update(input, offset, len) }

        override fun doFinal(): ByteArray = digest.digest()

        override fun reset() {
            digest.reset()
            digest.update(keyBlock)
        }

        override fun reset(newKey: ByteArray) {
            val oldDigest = digest
            this.digest = factory.keyedHashInstance(
                bitStrength = bitStrength,
                keyLength = newKey.size,
                salt = null,
                personalization = personalization,
            )

            newKey.copyInto(keyBlock)
            keyBlock.fill(0, newKey.size)
            digest.update(keyBlock)
            oldDigest.reset()
        }

        override fun algorithm(): String = digest.algorithm()

        override fun macLength(): Int = digest.digestLength()
    }
}
