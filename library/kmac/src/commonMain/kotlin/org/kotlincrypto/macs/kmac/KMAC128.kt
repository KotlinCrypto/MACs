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
@file:Suppress("SpellCheckingInspection", "LocalVariableName")

package org.kotlincrypto.macs.kmac

import org.kotlincrypto.core.mac.Mac
import org.kotlincrypto.core.xof.Xof
import org.kotlincrypto.error.InvalidKeyException
import org.kotlincrypto.error.InvalidParameterException
import org.kotlincrypto.hash.sha3.CSHAKE128
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

/**
 * KMAC128 implementation
 *
 * https://nvlpubs.nist.gov/nistpubs/SpecialPublications/NIST.SP.800-185.pdf#4%20KMAC
 *
 * @see [xOf]
 * */
public class KMAC128: Kmac {

    /**
     * Creates a new [KMAC128] [Mac] instance with a default output length
     * of 32 bytes.
     *
     * @throws [InvalidKeyException] if [key] size is less than 1
     * */
    public constructor(
        key: ByteArray,
    ): this(key, null)

    /**
     * Creates a new [KMAC128] [Mac] instance with a default output length
     * of 32 bytes.
     *
     * @param [S] A user selected customization bit string to define a variant
     *   of the function. When no customization is desired, [S] is set to an
     *   empty or null value. (e.g. "My Customization".encodeToByteArray()).
     *
     * @throws [InvalidKeyException] if [key] size is less than 1
     * */
    public constructor(
        key: ByteArray,
        S: ByteArray?,
    ): this(key, S, MAC_LENGTH)

    /**
     * Creates a new [KMAC128] [Mac] instance with a non-default output length.
     *
     * @param [S] A user selected customization bit string to define a variant
     *   of the function. When no customization is desired, [S] is set to an
     *   empty or null value. (e.g. "My Customization".encodeToByteArray()).
     * @param [outputLength] The number of bytes returned when [doFinal] is invoked
     *
     * @throws [InvalidKeyException] if [key] size is less than 1
     * @throws [InvalidParameterException] if [outputLength] is negative
     * */
    public constructor(
        key: ByteArray,
        S: ByteArray?,
        outputLength: Int,
    ): this(key, S, outputLength, xofMode = false)

    @Throws(InvalidKeyException::class, InvalidParameterException::class)
    private constructor(
        key: ByteArray,
        S: ByteArray?,
        outputLength: Int,
        xofMode: Boolean,
    ): super(
        key = key,
        S = S,
        bitStrength = BIT_STRENGTH_128,
        outputLength = outputLength,
        xofMode = xofMode,
    )

    private constructor(other: KMAC128): super(other)

    public override fun copy(): KMAC128 = KMAC128(this)

    public companion object: KMACXofFactory<KMAC128>() {

        /**
         * The default number of bytes output when [doFinal] is invoked (32)
         * */
        public const val MAC_LENGTH: Int = CSHAKE128.DIGEST_LENGTH

        /**
         * Produces a new [Xof] (Extendable-Output Function) for [KMAC128]
         *
         * @see [Xof.Companion.reset]
         * @param [S] A user selected customization bit string to define a variant
         *   of the function. When no customization is desired, [S] is set to an
         *   empty or null value. (e.g. "My Customization".encodeToByteArray()).
         *
         * @throws [InvalidKeyException] if [key] size is less than 1
         * */
        @JvmStatic
        @JvmOverloads
        public fun xOf(
            key: ByteArray,
            S: ByteArray? = null,
        ): Xof<KMAC128> = KMACXof(KMAC128(key, S, outputLength = 0, xofMode = true))
    }
}
