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
package org.kotlincrypto.macs

import org.bouncycastle.crypto.macs.KMAC
import org.bouncycastle.crypto.params.KeyParameter
import org.kotlincrypto.core.Updatable

class BCKmacWrapper(bitStrength: Int, key: ByteArray): Updatable {
    val kmac = KMAC(bitStrength, null)

    init {
        kmac.init(KeyParameter(key))
    }

    override fun update(input: Byte) { kmac.update(input) }
    override fun update(input: ByteArray) { kmac.update(input, 0, input.size) }
    override fun update(input: ByteArray, offset: Int, len: Int) { kmac.update(input, offset, len) }
}