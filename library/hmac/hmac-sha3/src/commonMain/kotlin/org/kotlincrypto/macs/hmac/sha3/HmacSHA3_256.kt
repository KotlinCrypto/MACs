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
import org.kotlincrypto.hash.sha3.SHA3_256
import org.kotlincrypto.macs.hmac.Hmac

/**
 * HmacSHA3-256 implementation
 * */
public class HmacSHA3_256: Hmac {

    /**
     * Instantiates a new instance of [HmacSHA3_256].
     *
     * @throws [IllegalArgumentException] if [key] is empty.
     * */
    @OptIn(InternalKotlinCryptoApi::class)
    @Throws(IllegalArgumentException::class)
    public constructor(key: ByteArray): super(key, "HmacSHA3-256", SHA3_256())

    @OptIn(InternalKotlinCryptoApi::class)
    private constructor(engine: Mac.Engine): super(engine)

    protected override fun copy(engineCopy: Mac.Engine): Mac = HmacSHA3_256(engineCopy)
}
