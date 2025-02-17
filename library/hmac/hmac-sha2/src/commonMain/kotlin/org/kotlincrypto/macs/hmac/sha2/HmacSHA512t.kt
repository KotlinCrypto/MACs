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
import org.kotlincrypto.error.InvalidParameterException
import org.kotlincrypto.hash.sha2.SHA512t
import org.kotlincrypto.macs.hmac.Hmac

/**
 * HmacSHA512/t implementation
 * */
public class HmacSHA512t: Hmac {

    /**
     * Creates a new instance of [HmacSHA512t].
     *
     * @throws [InvalidKeyException] if [key] size is less than 1
     * @throws [InvalidParameterException] when:
     *  - [t] is less than 8
     *  - [t] is greater than or equal to 512
     *  - [t] is not a factor of 8
     *  - [t] is 384
     * */
    public constructor(key: ByteArray, t: Int): super(
        key = key,
        algorithm = "HmacSHA512/$t",
        digest = SHA512t(t),
    )

    private constructor(other: HmacSHA512t): super(other)

    public override fun copy(): HmacSHA512t = HmacSHA512t(this)
}
