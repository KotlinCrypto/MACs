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
package org.kotlincrypto.macs.hmac.sha3

import org.kotlincrypto.core.mac.Mac
import org.kotlincrypto.macs.MacUnitTest
import kotlin.test.Test

@Suppress("ClassName")
open class HmacSHA3_512UnitTest: MacUnitTest() {
    override fun mac(key: ByteArray): Mac = HmacSHA3_512(key)

    final override val expectedResetSmallHash: String = "5c4e018be6d40bc6cf6e30ce8e223c84ff0f1107513804c3e14c522d787146c554273068d5f6b57f94d27b2c9aa10dab07c08ddeb5e7e7f73366b2314aa6afe3"
    final override val expectedResetMediumHash: String = "8449fa02ad3a0197d0bcef5ca1ee638d0eab5c738e57dda12ad2a624ed39a9a2617aeb7eba69b40ae7a2109a016a417629d45beba62d9f95152863f7268bbd18"
    final override val expectedResetLargeHash: String = "e25e5bfac2848edf46d0d1798349f6c5658db8f5181206f9c097e19753f18ced68d58252827b8ed863b067fce1d4eb75ab111926674bc908c2aa19f59b69f4c9"
    final override val expectedUpdateSmallHash: String = "fd4e02105db8bd79c1ccfcac5b728d5f36c73facb7fec4512cb4ad1b93dec94f25a77beae81d6d2f2de4dd37c8cd8244d1b9ce5f68f47ee536d2ce2db3930f71"
    final override val expectedUpdateMediumHash: String = "af61cf6bf80b5f9843bb9652c3f49077e2316fb40bd008fe7d72133d54e2aa61938335342d400f9420530a81aad63026c81ccd5c37da0a5fbe9aace2673298c7"

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

    @Test
    final override fun givenMac_whenInstanceResetWithNewKey_thenDoFinalReturnsExpected() {
        super.givenMac_whenInstanceResetWithNewKey_thenDoFinalReturnsExpected()
    }
}
