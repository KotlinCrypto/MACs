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

open class HmacSHA224UnitTest: MacUnitTest() {
    override fun mac(key: ByteArray): Mac = HmacSHA224(key)

    final override val expectedResetSmallHash: String = "13319a64882e576bc8978ad82168852acc6457634682d1fe7314a228"
    final override val expectedResetMediumHash: String = "368b9a70df48583d9dd85a90da13a92be542d6e2876796219db48bc4"
    final override val expectedResetLargeHash: String = "19ee86d9e9a7d39f4859945ed10d68ba79d192ea16b393cd68246f09"
    final override val expectedUpdateSmallHash: String = "c48497a37d517e65b53e0bd6a0a4d9daa21f5f395d11b9e4d39d4af5"
    final override val expectedUpdateMediumHash: String = "48b0f548c252d41949cdaced42f1b3f53ee52bb9f97b3259d396f973"

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
