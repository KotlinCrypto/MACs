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

import org.kotlincrypto.hash.sha2.SHA256
import org.kotlincrypto.macs.hmac.Hmac

/**
 * HmacSHA256 implementation
 * */
public class HmacSHA256: Hmac {

    /**
     * Creates a new instance of [HmacSHA256].
     *
     * @throws [IllegalArgumentException] if [key] is empty.
     * */
    public constructor(key: ByteArray): super(
        key = key,
        algorithm = "HmacSHA256",
        digest = SHA256(),
    )

    private constructor(other: HmacSHA256): super(other)

    public override fun copy(): HmacSHA256 = HmacSHA256(this)
}
