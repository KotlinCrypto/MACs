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
@file:Suppress("UnnecessaryOptInAnnotation", "FunctionName")

package org.kotlincrypto.macs

import org.kotlincrypto.core.InternalKotlinCryptoApi
import org.kotlincrypto.core.Mac
import org.kotlincrypto.hash.sha2.SHA512t

/**
 * HmacSHA512/224
 *
 * @throws [IllegalArgumentException] if [key] is empty
 * */
@Throws(IllegalArgumentException::class)
public fun HmacSHA512_224(key: ByteArray): HmacSHA512t = HmacSHA512t(key, 224)

/**
 * HmacSHA512/256
 *
 * @throws [IllegalArgumentException] if [key] is empty
 * */
@Throws(IllegalArgumentException::class)
public fun HmacSHA512_256(key: ByteArray): HmacSHA512t = HmacSHA512t(key, 256)

/**
 * HmacSHA512/t implementation
 * */
public class HmacSHA512t: Hmac {

    /**
     * Instantiates a new instance of [HmacSHA512t].
     *
     * @see [HmacSHA512_224]
     * @see [HmacSHA512_256]
     * @throws [IllegalArgumentException] when:
     *  - [key] is empty
     *  - [t] is less than 8
     *  - [t] is greater than or equal to 512
     *  - [t] is not a factor of 8
     *  - [t] is 384
     * */
    @OptIn(InternalKotlinCryptoApi::class)
    @Throws(IllegalArgumentException::class)
    public constructor(key: ByteArray, t: Int): super(key, "HmacSHA512/$t", SHA512t(t))

    @OptIn(InternalKotlinCryptoApi::class)
    private constructor(algorithm: String, engine: Engine): super(algorithm, engine)

    protected override fun copy(engineCopy: Mac.Engine): Mac = HmacSHA512t(algorithm(), engineCopy as Engine)
}
