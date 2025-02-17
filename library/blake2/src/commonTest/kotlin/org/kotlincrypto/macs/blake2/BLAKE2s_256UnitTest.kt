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

open class BLAKE2s_256UnitTest: MacUnitTest() {
    override fun mac(key: ByteArray): Mac = BLAKE2s(key, 256)

    final override val maxKeyLength: Int = 32

    final override val expectedResetSmallHash: String = "24a80aed26a17c9210503918bf9b4c0aeb9e2e03f2ee9b3cf636f0325a27b745"
    final override val expectedResetMediumHash: String = "7a50d2c8aff303387f706576ee03a1815bcaa3f69287a0b208b34a2995b0cea7"
    final override val expectedResetLargeHash: String = "1042e44eb3451dd8a57ee87499dd14b3865e9a7e6825cdc664856742a2792264"
    final override val expectedUpdateSmallHash: String = "d41121edf9fe5910bffcdab5e48004468ef4f121a52204fbf97052b1b0241ef7"
    final override val expectedUpdateMediumHash: String = "863ad2cab99016e2bfdf7e680887fc468f81eb6838848d06c45bfd779f817358"

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
