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
@file:Suppress("UnnecessaryOptInAnnotation")

package org.kotlincrypto.macs

import org.kotlincrypto.core.InternalKotlinCryptoApi
import org.kotlincrypto.core.Mac
import javax.crypto.spec.SecretKeySpec

/**
 * Simple test class that wraps a [javax.crypto.Mac]
 * with [org.kotlincrypto.core.Mac]
 * */
@OptIn(InternalKotlinCryptoApi::class)
class TestJvmMac: Mac {

    constructor(algorithm: String, key: ByteArray): super(algorithm, Engine(algorithm, key))

    private constructor(algorithm: String, engine: Engine): super(algorithm, engine)
    override fun copy(engineCopy: Mac.Engine): Mac = TestJvmMac(algorithm, engineCopy as Engine)

    private class Engine: Mac.Engine {

        private val delegate: javax.crypto.Mac

        constructor(algorithm: String, key: ByteArray): super(key) {
            delegate = getInstance(algorithm).apply {
                init(SecretKeySpec(key, algorithm))
            }
        }

        private constructor(state: State, engine: Engine): super(state) {
            delegate = engine.delegate.clone() as javax.crypto.Mac
        }

        override fun copy(): Mac.Engine = Engine(object : State() {}, this)

        override fun macLength(): Int = delegate.macLength

        override fun update(input: Byte) { delegate.update(input) }
        override fun update(input: ByteArray) { delegate.update(input) }
        override fun update(input: ByteArray, offset: Int, len: Int) { delegate.update(input, offset, len) }

        override fun doFinal(): ByteArray = delegate.doFinal()

        override fun reset() { delegate.reset() }
    }
}
