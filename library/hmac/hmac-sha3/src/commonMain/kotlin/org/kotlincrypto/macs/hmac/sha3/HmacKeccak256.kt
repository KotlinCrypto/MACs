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
@file:Suppress("ClassName", "UnnecessaryOptInAnnotation")

package org.kotlincrypto.macs.hmac.sha3

import org.kotlincrypto.core.InternalKotlinCryptoApi
import org.kotlincrypto.core.mac.Mac
import org.kotlincrypto.hash.sha3.Keccak256
import org.kotlincrypto.macs.hmac.Hmac

/**
 * HmacKeccak256 implementation
 * */
public class HmacKeccak256: Hmac {

    /**
     * Instantiates a new instance of [HmacKeccak256].
     *
     * @throws [IllegalArgumentException] if [key] is empty.
     * */
    @OptIn(InternalKotlinCryptoApi::class)
    @Throws(IllegalArgumentException::class)
    public constructor(key: ByteArray): super(key, "HmacKeccak256", Keccak256())

    @OptIn(InternalKotlinCryptoApi::class)
    private constructor(engine: Mac.Engine): super(engine)

    protected override fun copy(engineCopy: Mac.Engine): Mac = HmacKeccak256(engineCopy)
}
