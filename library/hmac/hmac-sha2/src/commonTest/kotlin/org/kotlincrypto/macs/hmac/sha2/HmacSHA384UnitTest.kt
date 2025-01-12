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
package org.kotlincrypto.macs.hmac.sha2

import org.kotlincrypto.core.mac.Mac
import org.kotlincrypto.macs.MacUnitTest
import kotlin.test.Test

open class HmacSHA384UnitTest: MacUnitTest() {
    override fun mac(key: ByteArray): Mac = HmacSHA384(key)

    final override val expectedResetSmallHash: String = "ad72d58dde8826ae6907a2326f016015dede372392d14cba08afe320201e66511a543f681ce5180ebee3c2a973d86437"
    final override val expectedResetMediumHash: String = "ff2e1c8024162d6c3ce583d6ad14a41f591483373f7a6be26b62fc0234385d25eab0543a49db5897192bd639049af2a8"
    final override val expectedResetLargeHash: String = "613c594bad7494a704788c236f6da9d9043a18a3c040001a148d912d9a1e018d5c228a2b0bf02b62d004c91c32f5c1a0"
    final override val expectedUpdateSmallHash: String = "ea98456cbf829dabad5af15199ba414e98d1cc3fbcf00058b41c5e9750795296b733fc3b5eaf14050afd521700c235f8"
    final override val expectedUpdateMediumHash: String = "d4abb28370815bcf29523948a3ba2019285feff348b5edf09f9c10743a6be723fc1883399a119209a210a0db0e17f8cc"

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
