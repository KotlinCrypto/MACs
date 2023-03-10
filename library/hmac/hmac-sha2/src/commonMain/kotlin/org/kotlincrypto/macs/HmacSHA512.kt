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
import org.kotlincrypto.hash.sha2.SHA512

/**
 * HmacSHA512 implementation
 * */
public class HmacSHA512: Hmac {

    /**
     * Instantiates a new instance of [HmacSHA512].
     *
     * @throws [IllegalArgumentException] if [key] is empty.
     * */
    @OptIn(InternalKotlinCryptoApi::class)
    @Throws(IllegalArgumentException::class)
    public constructor(key: ByteArray): super(key, "HmacSHA512", SHA512())

    @OptIn(InternalKotlinCryptoApi::class)
    private constructor(algorithm: String, engine: Engine): super(algorithm, engine)

    protected override fun copy(engineCopy: Mac.Engine): Mac = HmacSHA512(algorithm(), engineCopy as Engine)
}
