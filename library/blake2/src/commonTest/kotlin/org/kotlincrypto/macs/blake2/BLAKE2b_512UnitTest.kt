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

open class BLAKE2b_512UnitTest: MacUnitTest() {
    override fun mac(key: ByteArray): Mac = BLAKE2b(key, 512)

    final override val maxKeyLength: Int = 64

    final override val expectedResetSmallHash: String = "3ee218a026889e1d9b93e6f1216415a6566344cd3634b0be5b529329bf95c519b6f1b5c663e390253b0815cd9cfe486a40bf6283d9e0de4420bcc72a54e34275"
    final override val expectedResetMediumHash: String = "185f29f5342ca4fce69eb3216852b1a87c9648879db85a89d251460f2234d6547f6758c4ad2c3fe21f1d23066dcbff46dd14b508dade008e69942f6fae68c328"
    final override val expectedResetLargeHash: String = "4c0ec45c0e365cf91c6839022a163deb8c4195f68bef616dd56039673f4e1b8589ce7d36738102ee057b907012e212c1c3c96149834a44d5e321756a9dc0bc60"
    final override val expectedUpdateSmallHash: String = "c293a90d4064c6e671e7ec6124b70952d25e1f2515abe498aa33c99fe131e35ce80049e6f2715eb4200a882b3b04892a57f0302d66f8df840f243588e3e96d99"
    final override val expectedUpdateMediumHash: String = "c153bb41a104b980d3346de6a7fe0ef9d11914b8a1dd023a95e3263bb4f2f546ed6ab83f0e8b2b8a434b53663cfbe40bfd04e9dbe119d0e81c9a793b08a10025"

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
