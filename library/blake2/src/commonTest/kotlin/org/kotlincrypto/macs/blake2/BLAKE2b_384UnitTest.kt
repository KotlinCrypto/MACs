/*
 * Copyright (c) 2025 KotlinCrypto
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
@file:Suppress("ClassName")

package org.kotlincrypto.macs.blake2

import org.kotlincrypto.core.mac.Mac
import org.kotlincrypto.macs.MacUnitTest
import kotlin.test.Test

open class BLAKE2b_384UnitTest: MacUnitTest() {
    override fun mac(key: ByteArray): Mac = BLAKE2b(key, 384)

    final override val maxKeyLength: Int = 64

    final override val expectedResetSmallHash: String = "a285da0d50d9664e847fd5f070e70c96ae7a5545032fd893dd87756803763e1654e488ff8d857986b39753e43faa864f"
    final override val expectedResetMediumHash: String = "612c0ad0a3b60257da991d969fdbcc45327bfcd074c00027659d1fc728b2de58f792b6d75ae2696f62da2c050df9a868"
    final override val expectedResetLargeHash: String = "7204d7c3d08f5698c81d95fcc54c58e79a50b9f68b8b911cfee91b916c5dd881f1d723f165d655fc595ab68199a610b5"
    final override val expectedUpdateSmallHash: String = "92545bfd677f6fed7da70bad2dae4f7f1f9fef7a0648e649e1ba30eaa1d2fa8077d94780b95863d25b032da29be71957"
    final override val expectedUpdateMediumHash: String = "eb6cc1e601b18c5139364d2349a3d7d1bfb71d6ac4b48c38e677751c928a774e2107767046e44d6b3f2cc1ef8211496a"

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

    @Test
    final override fun givenMac_whenInstanceResetWithNewKey_thenDoFinalReturnsExpected() {
        super.givenMac_whenInstanceResetWithNewKey_thenDoFinalReturnsExpected()
    }
}
