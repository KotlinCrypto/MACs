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

package org.kotlincrypto.macs.hmac.sha3

import org.kotlincrypto.hash.sha3.Keccak224
import org.kotlincrypto.macs.hmac.Hmac

/**
 * HmacKeccak224 implementation
 * */
public class HmacKeccak224: Hmac {

    /**
     * Creates a new instance of [HmacKeccak224].
     *
     * @throws [IllegalArgumentException] if [key] is empty.
     * */
    public constructor(key: ByteArray): super(
        key = key,
        algorithm = "HmacKeccak224",
        digest = Keccak224(),
    )

    private constructor(other: HmacKeccak224): super(other)

    public override fun copy(): HmacKeccak224 = HmacKeccak224(this)
}
