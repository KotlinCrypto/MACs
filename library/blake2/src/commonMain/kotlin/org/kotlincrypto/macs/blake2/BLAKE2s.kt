/*
 * Copyright (c) 2025 KotlinCrypto
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
@file:OptIn(InternalKotlinCryptoApi::class)

package org.kotlincrypto.macs.blake2

import org.kotlincrypto.core.InternalKotlinCryptoApi
import org.kotlincrypto.error.InvalidKeyException
import org.kotlincrypto.error.InvalidParameterException
import org.kotlincrypto.hash.blake2.BLAKE2s as BLAKE2sDigest

/**
 * BLAKE2s Keyed Hashing implementation
 *
 * https://datatracker.ietf.org/doc/rfc7693/
 *
 * https://www.blake2.net/blake2.pdf
 * */
public class BLAKE2s: BLAKE2Mac {

    /**
     * Creates a new instance of [BLAKE2s].
     *
     * @param [key] The secret to use for authenticating data.
     * @param [bitStrength] The number of bits returned when [doFinal] is invoked.
     *
     * @throws [InvalidParameterException] when:
     *  - [bitStrength] is less than 8
     *  - [bitStrength] is greater than 256
     *  - [bitStrength] is not a factor of 8
     * @throws [InvalidKeyException] when:
     *  - [key] size is less than 1
     *  - [key] size is greater than 32
     * */
    public constructor(
        key: ByteArray,
        bitStrength: Int,
    ): this(key, bitStrength, null)

    /**
     * Creates a new instance of [BLAKE2s].
     *
     * @param [key] The secret to use for authenticating data.
     * @param [bitStrength] The number of bits returned when [doFinal] is invoked.
     * @param [personalization] A user selected customization bit string to define a variant
     *   of the function. When no customization is desired, [personalization] must be set to a
     *   null value. Must be 8 bytes in length, or null.
     *
     * @throws [InvalidParameterException] when:
     *  - [bitStrength] is less than 8
     *  - [bitStrength] is greater than 256
     *  - [bitStrength] is not a factor of 8
     *  - [personalization] is non-null and not exactly 8 bytes in length
     * @throws [InvalidKeyException] when:
     *  - [key] size is less than 1
     *  - [key] size is greater than 32
     * */
    public constructor(
        key: ByteArray,
        bitStrength: Int,
        personalization: ByteArray?,
    ): super(
        key = key,
        bitStrength = bitStrength,
        personalization = personalization,
        factory = ::BLAKE2sDigest,
    )

    private constructor(other: BLAKE2s): super(other)

    public override fun copy(): BLAKE2s = BLAKE2s(this)
}
