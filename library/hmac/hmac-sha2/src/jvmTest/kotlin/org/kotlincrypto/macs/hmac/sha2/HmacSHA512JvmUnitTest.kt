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
package org.kotlincrypto.macs.hmac.sha2

import org.kotlincrypto.core.mac.Mac
import org.kotlincrypto.macs.TestJvmMac

class HmacSHA512JvmUnitTest: HmacSHA512UnitTest() {
    override fun mac(key: ByteArray): Mac = TestJvmMac(super.mac(key).algorithm(), key)
}
