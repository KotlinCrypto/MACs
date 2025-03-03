/*
 * Copyright (c) 2023 KotlinCrypto
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
package org.kotlincrypto.macs

import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.kotlincrypto.core.mac.Mac
import java.security.NoSuchAlgorithmException
import java.security.Security
import javax.crypto.spec.SecretKeySpec

/**
 * Simple test class that wraps a [javax.crypto.Mac]
 * with [org.kotlincrypto.core.mac.Mac]
 * */
class TestJvmMac: Mac {

    constructor(algorithm: String, key: ByteArray): super(algorithm, Engine(algorithm, key))

    private constructor(other: TestJvmMac): super(other)
    override fun copy(): TestJvmMac = TestJvmMac(this)

    private class Engine: Mac.Engine {

        private val delegate: javax.crypto.Mac

        constructor(algorithm: String, key: ByteArray): super(key) {
            delegate = provideInstance(algorithm).apply {
                init(SecretKeySpec(key, algorithm))
            }
        }

        private constructor(other: Engine): super(other) {
            delegate = other.delegate.clone() as javax.crypto.Mac
        }

        override fun copy(): Engine = Engine(this)

        override fun macLength(): Int = delegate.macLength

        override fun update(input: Byte) { delegate.update(input) }
        override fun update(input: ByteArray) { delegate.update(input) }
        override fun update(input: ByteArray, offset: Int, len: Int) { delegate.update(input, offset, len) }

        override fun doFinal(): ByteArray = delegate.doFinal()
        override fun doFinalInto(dest: ByteArray, destOffset: Int) {
            delegate.doFinal(dest, destOffset)
        }

        override fun reset() { delegate.reset() }
        override fun reset(newKey: ByteArray) { delegate.init(SecretKeySpec(newKey, delegate.algorithm)) }
    }

    private companion object {

        /**
         * Will fall back to using [BouncyCastleProvider] in the event
         * an algorithm is not available via Java.
         * */
        @Throws(NoSuchAlgorithmException::class)
        private fun provideInstance(algorithm: String): javax.crypto.Mac {
            try {
                return getInstance(algorithm)
            } catch (_: NoSuchAlgorithmException) {
                synchronized(this) {
                    if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
                        Security.addProvider(BouncyCastleProvider())
                    }

                    return getInstance(algorithm)
                }
            }
        }
    }
}
