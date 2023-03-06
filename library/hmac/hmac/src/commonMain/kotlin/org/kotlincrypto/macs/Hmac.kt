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
import kotlin.jvm.JvmSynthetic

/**
 * Core abstraction for Hash-based Message Authentication
 * Code implementations.
 *
 * @see [Mac]
 * @see [Digest]
 * */
public abstract class Hmac: Mac {

    @InternalKotlinCryptoApi
    @Throws(IllegalArgumentException::class)
    protected constructor(
        key: ByteArray,
        algorithm: String,
        digest: Digest,
    ): super(algorithm, Engine.instance(key, digest))

    @InternalKotlinCryptoApi
    protected constructor(
        algorithm: String,
        engine: Hmac.Engine,
    ): super(algorithm, engine)

    protected class Engine: Mac.Engine {

        private val state: State

        @OptIn(InternalKotlinCryptoApi::class)
        private constructor(key: ByteArray, digest: Digest): super(key) {
            digest.reset()

            val preparedKey = if (key.size > digest.blockSize()) {
                digest.digest(key).copyOf(digest.blockSize())
            } else if (key.size < digest.blockSize()) {
                key.copyOf(digest.blockSize())
            } else {
                key
            }

            state = State(
                iKey = ByteArray(digest.blockSize()) { i -> preparedKey[i] xor I_PAD },
                oKey = ByteArray(digest.blockSize()) { i -> preparedKey[i] xor O_PAD },
                digest = digest,
            )

            digest.update(state.iKey)
        }

        @OptIn(InternalKotlinCryptoApi::class)
        private constructor(state: State): super(state) {
            this.state = State(state.iKey, state.oKey, state.digest.copy())
        }

        public override fun update(input: Byte) { state.digest.update(input) }
        public override fun update(input: ByteArray) { state.digest.update(input) }
        public override fun update(input: ByteArray, offset: Int, len: Int) { state.digest.update(input, offset, len) }

        public override fun doFinal(): ByteArray {
            return with(state) {
                val iFinal = digest.digest()
                digest.update(oKey)

                val oFinal = digest.digest(iFinal)

                // Prepare digest for reuse
                digest.update(iKey)

                oFinal
            }
        }

        public override fun reset() {
            with(state) {
                digest.reset()
                digest.update(iKey)
            }
        }

        public override fun macLength(): Int = state.digest.digestLength()

        public override fun copy(): Engine = Engine(state)

        private inner class State(
            val iKey: ByteArray,
            val oKey: ByteArray,
            val digest: Digest,
        ): Mac.Engine.State()

        internal companion object {
            private const val I_PAD: Byte = 0x36
            private const val O_PAD: Byte = 0x5C

            @JvmSynthetic
            internal fun instance(key: ByteArray, digest: Digest): Engine = Engine(key, digest)
        }
    }
}
