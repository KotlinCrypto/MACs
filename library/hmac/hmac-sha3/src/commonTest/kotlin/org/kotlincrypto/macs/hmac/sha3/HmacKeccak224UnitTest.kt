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

open class HmacKeccak224UnitTest: MacUnitTest() {
    override fun mac(key: ByteArray): Mac = HmacKeccak224(key)

    final override val expectedResetSmallHash: String = "66264563097aefdd48c72da35bf84ff97f756792931aea3b7393e557"
    final override val expectedResetMediumHash: String = "aba0d5ebe751687fe14f4c4225d02d353c7f1717e36d72e444913ca2"
    final override val expectedResetLargeHash: String = "2fc1b1cf73f8938a1e2d7f34771e76aedcc50c50d7e40ce836fe1558"
    final override val expectedUpdateSmallHash: String = "4fcda03415fddb49cce47036ab2a051dbac3121c8537bf19f1347371"
    final override val expectedUpdateMediumHash: String = "a399c31fa4f0396fc3ae9bae224904d8393ec1b063b90602eed8902f"

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
