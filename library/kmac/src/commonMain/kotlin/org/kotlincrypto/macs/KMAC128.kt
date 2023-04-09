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
package org.kotlincrypto.macs

import org.kotlincrypto.core.Mac
import org.kotlincrypto.core.Xof
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

/**
 * KMAC128 implementation
 * */
public class KMAC128: Kmac {

    /**
     * Creates a new [KMAC128] [Mac] instance with a fixed output [macLength].
     *
     * @throws [IllegalArgumentException] if [key] is empty.
     * */
    @Throws(IllegalArgumentException::class)
    public constructor(key: ByteArray): this(key, null)

    /**
     * Creates a new [KMAC128] [Mac] instance with a fixed output [macLength].
     *
     * @param [S] A user selected customization bit string to define a variant
     *   of the function. When no customization is desired, [S] is set to an
     *   empty or null value. (e.g. "My Customization".encodeToByteArray()).
     * @throws [IllegalArgumentException] if [key] is empty.
     * */
    @Throws(IllegalArgumentException::class)
    public constructor(key: ByteArray, S: ByteArray?): super(key, S, BIT_STRENGTH_128)

    private constructor(engine: Mac.Engine): super(engine)

    protected override fun copy(engineCopy: Mac.Engine): Mac = KMAC128(engineCopy)

    public companion object: KMACXofFactory<KMAC128>() {

        /**
         * Produces a new [Xof] (Extendable-Output Function) for [KMAC128]
         *
         * @param [S] A user selected customization bit string to define a variant
         *   of the function. When no customization is desired, [S] is set to an
         *   empty or null value. (e.g. "My Customization".encodeToByteArray()).
         * @throws [IllegalArgumentException] if [key] is empty.
         * */
        @JvmStatic
        @JvmOverloads
        public fun xOf(key: ByteArray, S: ByteArray? = null): Xof<KMAC128> = KMACXof(KMAC128(key, S))
    }
}
