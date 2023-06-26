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
@file:Suppress("ClassName")

package org.kotlincrypto.macs

import org.kotlincrypto.core.mac.Mac
import kotlin.test.Test

open class HmacSHA512_224UnitTest: MacUnitTest() {
    override fun mac(key: ByteArray): Mac = HmacSHA512_224(key)

    final override val expectedResetSmallHash: String = "22c57dcc73cdacef1ebb24d9870dce63a8b0948400be51023743cfe0"
    final override val expectedResetMediumHash: String = "53e7ffd5bb5e3a93d9bac8966ed9728c3311010c6bc62be9793640a5"
    final override val expectedResetLargeHash: String = "3702635500e25c0831e1adf9ea7af501c0253782495dacbb69b2aaf3"
    final override val expectedUpdateSmallHash: String = "1ed0d1b7f21b649bd7db9cd0fe617b0940711537d34e8c36f582a0a9"
    final override val expectedUpdateMediumHash: String = "3cfd795031e641cd7e7173b595e4ecbcf3f909e2c8eb67dca641cca4"

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
