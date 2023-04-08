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

@Suppress("ClassName")
open class HmacSHA3_224UnitTest: MacUnitTest() {
    override fun mac(key: ByteArray): Mac = HmacSHA3_224(key)

    final override val expectedResetSmallHash: String = "3a6e2a4dfa32d75da48c53c233383385d2c316f13867ea77e4f7c9b4"
    final override val expectedResetMediumHash: String = "0feff2181a988c070f6f019801b6e0fc8a18e9ce7c652a76fc819036"
    final override val expectedResetLargeHash: String = "b1d207a4c557960d53cad12dd0933fdffff53cf236cf010ffe96713a"
    final override val expectedUpdateSmallHash: String = "084b57069bb4fb9e08c5c5d84bea1117dfe3a36ed23b6ac4957359d2"
    final override val expectedUpdateMediumHash: String = "fafe677adc1e131c723f096a9009387ab4449041316644c11f1ebc7c"

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
