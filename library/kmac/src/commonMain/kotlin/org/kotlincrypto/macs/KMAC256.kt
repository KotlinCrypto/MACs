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
import org.kotlincrypto.hash.sha3.CSHAKE256
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

/**
 * KMAC128 implementation
 *
 * @see [xOf]
 * */
public class KMAC256: Kmac {

    /**
     * Creates a new [KMAC256] [Mac] instance with a default output length
     * of 64 bytes.
     *
     * @throws [IllegalArgumentException] if [key] is empty.
     * */
    @Throws(IllegalArgumentException::class)
    public constructor(
        key: ByteArray,
    ): this(key, null)

    /**
     * Creates a new [KMAC256] [Mac] instance with a default output length
     * of 64 bytes.
     *
     * @param [S] A user selected customization bit string to define a variant
     *   of the function. When no customization is desired, [S] is set to an
     *   empty or null value. (e.g. "My Customization".encodeToByteArray()).
     * @throws [IllegalArgumentException] if [key] is empty.
     * */
    @Throws(IllegalArgumentException::class)
    public constructor(
        key: ByteArray,
        S: ByteArray?,
    ): this(key, S, MAC_LENGTH)

    /**
     * Creates a new [KMAC256] [Mac] instance with a non-default output length.
     *
     * @param [S] A user selected customization bit string to define a variant
     *   of the function. When no customization is desired, [S] is set to an
     *   empty or null value. (e.g. "My Customization".encodeToByteArray()).
     * @param [outputLength] The number of bytes returned when [doFinal] is invoked
     * @throws [IllegalArgumentException] if [key] is empty, or [outputLength]
     *   is negative.
     * */
    @Throws(IllegalArgumentException::class)
    public constructor(
        key: ByteArray,
        S: ByteArray?,
        outputLength: Int,
    ): super(key, S, BIT_STRENGTH_256, outputLength)

    private constructor(engine: Mac.Engine): super(engine)

    protected override fun copy(engineCopy: Mac.Engine): Mac = KMAC256(engineCopy)

    public companion object: KMACXofFactory<KMAC256>() {

        /**
         * The default number of bytes output when [doFinal] is invoked (64)
         * */
        public const val MAC_LENGTH: Int = CSHAKE256.DIGEST_LENGTH

        /**
         * Produces a new [Xof] (Extendable-Output Function) for [KMAC256]
         *
         * @param [S] A user selected customization bit string to define a variant
         *   of the function. When no customization is desired, [S] is set to an
         *   empty or null value. (e.g. "My Customization".encodeToByteArray()).
         * @throws [IllegalArgumentException] if [key] is empty.
         * */
        @JvmStatic
        @JvmOverloads
        public fun xOf(key: ByteArray, S: ByteArray? = null): Xof<KMAC256> = KMACXof(KMAC256(key, S, 0))
    }
}
