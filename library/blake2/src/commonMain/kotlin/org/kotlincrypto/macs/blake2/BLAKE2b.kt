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

import org.kotlincrypto.core.InternalKotlinCryptoApi

/**
 * BLAKE2b Keyed Hashing implementation
 *
 * https://datatracker.ietf.org/doc/rfc7693/
 * */
public class BLAKE2b: BLAKE2Mac {

    /**
     * Creates a new instance of [BLAKE2b].
     *
     * @param [key] The secret to use for authenticating data.
     * @param [bitStrength] The number of bits returned when [doFinal] is invoked.
     *
     * @throws [IllegalArgumentException] when:
     *  - [key] size is less than 1
     *  - [key] size is greater than 64
     *  - [bitStrength] is less than 8
     *  - [bitStrength] is greater than 512
     *  - [bitStrength] is not a factor of 8
     * */
    public constructor(
        key: ByteArray,
        bitStrength: Int,
    ): this(key, bitStrength, null)

    /**
     * Creates a new instance of [BLAKE2b].
     *
     * @param [key] The secret to use for authenticating data.
     * @param [bitStrength] The number of bits returned when [doFinal] is invoked.
     * @param [personalization] A user selected customization bit string to define a variant
     *   of the function. When no customization is desired, [personalization] must be set to a
     *   null value. Must be 16 bytes in length, or null.
     *
     * @throws [IllegalArgumentException] when:
     *  - [key] size is less than 1
     *  - [key] size is greater than 64
     *  - [bitStrength] is less than 8
     *  - [bitStrength] is greater than 512
     *  - [bitStrength] is not a factor of 8
     *  - [personalization] is non-null and not exactly 16 bytes in length
     * */
    public constructor(
        key: ByteArray,
        bitStrength: Int,
        personalization: ByteArray?,
    ): super(
        key = key,
        bitStrength = bitStrength,
        personalization = personalization,
        factory = Companion,
    )

    private constructor(other: BLAKE2b): super(other)

    public override fun copy(): BLAKE2b = BLAKE2b(this)

    private companion object: DigestFactory {

        @OptIn(InternalKotlinCryptoApi::class)
        override fun newInstance(
            bitStrength: Int,
            keyLength: Int,
            salt: ByteArray?,
            personalization: ByteArray?,
        ): org.kotlincrypto.hash.blake2.BLAKE2b = org.kotlincrypto.hash.blake2.BLAKE2b(
            bitStrength = bitStrength,
            keyLength = keyLength,
            salt = salt,
            personalization = personalization,
        )
    }
}
