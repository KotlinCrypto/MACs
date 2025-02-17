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
package org.kotlincrypto.macs.blake2

import org.bouncycastle.crypto.ExtendedDigest
import org.bouncycastle.crypto.digests.Blake2bDigest
import org.bouncycastle.crypto.digests.Blake2sDigest
import org.kotlincrypto.core.mac.Mac

/**
 * Simple test class that Bouncy Castle Blake2bDigest and Blake2sDigest
 * */
class BLAKE2JvmMac: Mac {

    constructor(algorithm: String, key: ByteArray): super(algorithm, Engine(algorithm, key))

    private constructor(other: BLAKE2JvmMac): super(other)
    override fun copy(): BLAKE2JvmMac = BLAKE2JvmMac(this)

    private class Engine: Mac.Engine {

        private val digestLength: Int
        private var delegate: ExtendedDigest

        constructor(algorithm: String, key: ByteArray): super(key) {
            val (name, digestLength) = algorithm.split('-').let {
                it.first() to (it.last().toInt() / Byte.SIZE_BITS)
            }
            this.digestLength = digestLength

            this.delegate = when (name) {
                "BLAKE2b" -> {
                    Blake2bDigest(key, digestLength, null, null)
                }
                "BLAKE2s" -> {
                    Blake2sDigest(key, digestLength, null, null)
                }
                else -> error("Unsupported $algorithm")
            }
        }

        private constructor(other: Engine): super(other) {
            this.digestLength = other.digestLength
            val otherDelegate = other.delegate
            this.delegate = when (otherDelegate) {
                is Blake2bDigest -> Blake2bDigest(otherDelegate)
                is Blake2sDigest -> Blake2sDigest(otherDelegate)
                else -> error("")
            }
        }

        override fun copy(): Engine = Engine(this)

        override fun macLength(): Int = delegate.digestSize

        override fun update(input: Byte) { delegate.update(input) }
        override fun update(input: ByteArray, offset: Int, len: Int) { delegate.update(input, offset, len) }

        override fun doFinal(): ByteArray {
            val out = ByteArray(macLength())
            delegate.doFinal(out, 0)
            return out
        }

        override fun reset() { delegate.reset() }
        override fun reset(newKey: ByteArray) {
            delegate = when (delegate) {
                is Blake2bDigest -> Blake2bDigest(newKey, digestLength, null, null)
                is Blake2sDigest -> Blake2sDigest(newKey, digestLength, null, null)
                else -> error("")
            }
        }
    }
}
