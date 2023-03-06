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

import org.kotlincrypto.core.Mac
import kotlin.test.Test

open class HmacSHA256UnitTest: MacUnitTest() {
    override fun mac(key: ByteArray): Mac = HmacSHA256(key)

    final override val expectedResetSmallHash: String = "f9464d2ac7487601361dcb545ceeb0cb07ffc7b3610053d9c227bf326eb33bea"
    final override val expectedResetMediumHash: String = "78945086111003f57e06016136c0e47249ffab57a7ee48141d928eb2574f79f0"
    final override val expectedResetLargeHash: String = "65f30f877dae4e847ffc7e79abcba18b4c0c995abf9f8a20a2d731e3405811f5"
    final override val expectedUpdateSmallHash: String = "0ae42195492346db2cb748c181e567e0118ad79dfb3233b8576562d4325de3b4"
    final override val expectedUpdateMediumHash: String = "39cb14ca739e34ab67d16c28af1de581509cc2743e7cf91f4196bf4f02614ebc"

    @Test
    override fun givenMac_whenResetSmallKey_thenDoFinalReturnsExpected() {
        super.givenMac_whenResetSmallKey_thenDoFinalReturnsExpected()
    }

    @Test
    override fun givenMac_whenResetMediumKey_thenDoFinalReturnsExpected() {
        super.givenMac_whenResetMediumKey_thenDoFinalReturnsExpected()
    }

    @Test
    override fun givenMac_whenResetLargeKey_thenDoFinalReturnsExpected() {
        super.givenMac_whenResetLargeKey_thenDoFinalReturnsExpected()
    }

    @Test
    override fun givenMac_whenUpdatedSmall_thenDoFinalReturnsExpected() {
        super.givenMac_whenUpdatedSmall_thenDoFinalReturnsExpected()
    }

    @Test
    override fun givenMac_whenUpdatedMedium_thenDoFinalReturnsExpected() {
        super.givenMac_whenUpdatedMedium_thenDoFinalReturnsExpected()
    }

    @Test
    override fun givenMac_whenCopied_thenIsDifferentInstance() {
        super.givenMac_whenCopied_thenIsDifferentInstance()
    }

    @Test
    override fun givenMac_whenDoFinal_thenLengthMatchesOutput() {
        super.givenMac_whenDoFinal_thenLengthMatchesOutput()
    }
}