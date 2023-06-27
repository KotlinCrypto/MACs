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
package org.kotlincrypto.macs.hmac.sha1

import org.kotlincrypto.core.mac.Mac
import org.kotlincrypto.macs.MacUnitTest
import kotlin.test.Test

open class HmacSHA1UnitTest: MacUnitTest() {
    override fun mac(key: ByteArray): Mac = HmacSHA1(key)

    final override val expectedResetSmallHash: String = "649492f2dbb7fd5d4d9299e7ad96c33d23c30855"
    final override val expectedResetMediumHash: String = "4bee82f6c3b3c782ddc5eb6e776c47891646081b"
    final override val expectedResetLargeHash: String = "6c8532ecb2060cda58e72f8cd8d45ffd2ca01858"
    final override val expectedUpdateSmallHash: String = "c48bce2bd2c036eebf08aeb1b5c57b5387632b0e"
    final override val expectedUpdateMediumHash: String = "d816e1912ba2cddf758d27fef55354b2bbf29bb7"

    @Test
    final override fun givenMac_whenResetSmallKey_thenDoFinalReturnsExpected() {
        super.givenMac_whenResetSmallKey_thenDoFinalReturnsExpected()
    }

    @Test
    final override fun givenMac_whenResetMediumKey_thenDoFinalReturnsExpected() {
        super.givenMac_whenResetMediumKey_thenDoFinalReturnsExpected()
    }

    @Test
    final override fun givenMac_whenResetLargeKey_thenDoFinalReturnsExpected() {
        super.givenMac_whenResetLargeKey_thenDoFinalReturnsExpected()
    }

    @Test
    final override fun givenMac_whenUpdatedSmall_thenDoFinalReturnsExpected() {
        super.givenMac_whenUpdatedSmall_thenDoFinalReturnsExpected()
    }

    @Test
    final override fun givenMac_whenUpdatedMedium_thenDoFinalReturnsExpected() {
        super.givenMac_whenUpdatedMedium_thenDoFinalReturnsExpected()
    }

    @Test
    final override fun givenMac_whenCopied_thenIsDifferentInstance() {
        super.givenMac_whenCopied_thenIsDifferentInstance()
    }

    @Test
    final override fun givenMac_whenDoFinal_thenLengthMatchesOutput() {
        super.givenMac_whenDoFinal_thenLengthMatchesOutput()
    }
}
