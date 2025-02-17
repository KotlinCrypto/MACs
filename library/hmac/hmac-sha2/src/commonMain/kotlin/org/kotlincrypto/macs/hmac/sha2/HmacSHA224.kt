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
@file:Suppress("SpellCheckingInspection")

package org.kotlincrypto.macs.hmac.sha2

import org.kotlincrypto.error.InvalidKeyException
import org.kotlincrypto.hash.sha2.SHA224
import org.kotlincrypto.macs.hmac.Hmac

/**
 * HmacSHA224 implementation
 * */
public class HmacSHA224: Hmac {

    /**
     * Creates a new instance of [HmacSHA224].
     *
     * @throws [InvalidKeyException] if [key] size is less than 1
     * */
    public constructor(key: ByteArray): super(
        key = key,
        algorithm = "HmacSHA224",
        digest = SHA224(),
    )

    private constructor(other: HmacSHA224): super(other)

    public override fun copy(): HmacSHA224 = HmacSHA224(this)
}
