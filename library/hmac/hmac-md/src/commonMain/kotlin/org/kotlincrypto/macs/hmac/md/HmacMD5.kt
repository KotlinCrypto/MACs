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
@file:Suppress("SpellCheckingInspection")

package org.kotlincrypto.macs.hmac.md

import org.kotlincrypto.error.InvalidKeyException
import org.kotlincrypto.hash.md.MD5
import org.kotlincrypto.macs.hmac.Hmac

/**
 * HmacMD5 implementation
 * */
public class HmacMD5: Hmac {

    /**
     * Creates a new instance of [HmacMD5].
     *
     * @throws [InvalidKeyException] if [key] size is less than 1
     * */
    public constructor(key: ByteArray): super(
        key = key,
        algorithm = "HmacMD5",
        digest = MD5(),
    )

    private constructor(other: HmacMD5): super(other)

    public override fun copy(): HmacMD5 = HmacMD5(this)
}
