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

open class HmacKeccak384UnitTest: MacUnitTest() {
    override fun mac(key: ByteArray): Mac = HmacKeccak384(key)

    final override val expectedResetSmallHash: String = "8017f38fbc2fa97035bba48bacd52f220c1773f03206a6b0620ff4d0655c13e02d6026385d9c7c83afdaa5fe241cadd1"
    final override val expectedResetMediumHash: String = "96a59240afe40503b76343d6f57a45b15c468d04477c1fb65c9268626453ac4697fa559a093de4a0fbefd53edaa86578"
    final override val expectedResetLargeHash: String = "5f3b0fde967be05193f1a6080832b87c30e259311a58c73c9f79745d592634c0c58baca0748d2f4095fba61055e75b6c"
    final override val expectedUpdateSmallHash: String = "7112da0c27c1c32b480c0b9c49709384017a4d513acf5592b324c8500c1522e1d6559f872a6f09c4bd0c59fca14f5d8e"
    final override val expectedUpdateMediumHash: String = "bec3ae1fea8ef4e83360c09741c7c53c638762f56e059603cf9429d1a94179e0d15ac7fee5124b77c6b01d057f78ea22"

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
