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

import org.kotlincrypto.core.mac.Mac
import kotlin.test.Test

@Suppress("ClassName")
open class HmacSHA3_256UnitTest: MacUnitTest() {
    override fun mac(key: ByteArray): Mac = HmacSHA3_256(key)

    final override val expectedResetSmallHash: String = "76076009403e2fc215b1b7f1997c0fd37c7e54baa7605d9a8fcc02b0da507023"
    final override val expectedResetMediumHash: String = "8fc352471d7bd136a0e9cf52c1c09f4eaa6ed5a88f84bdfb3010e9e22f9e4f1f"
    final override val expectedResetLargeHash: String = "806ceecdcb6ed32da4af8cb7758ad1580b52a55e6ee8e7112770ad1efbe5619a"
    final override val expectedUpdateSmallHash: String = "f703257caaf1c85ff8fe1f2db3fa0b4ce099ec5e36fd1b7991ac4942dc8a9193"
    final override val expectedUpdateMediumHash: String = "1662a54a7eb74905543c45d8eeb92e999ce5093f936a85726d90cfc23970095f"

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
    override fun givenMac_whenCopied_thenIsDifferentInstance() {
        super.givenMac_whenCopied_thenIsDifferentInstance()
    }

    @Test
    final override fun givenMac_whenDoFinal_thenLengthMatchesOutput() {
        super.givenMac_whenDoFinal_thenLengthMatchesOutput()
    }
}
