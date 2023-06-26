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

open class HmacSHA512_256UnitTest: MacUnitTest() {
    override fun mac(key: ByteArray): Mac = HmacSHA512_256(key)

    final override val expectedResetSmallHash: String = "5a9c3f3b04f27901c6f61b4f6e2c594c2e92bc782143d073791994b563b4480e"
    final override val expectedResetMediumHash: String = "447f9db5826247b823f272922f4eaf6f2651c0a30afacfd294d94d3239d2e4ed"
    final override val expectedResetLargeHash: String = "4eb647c60ac995adefcbe0d8c25e7863083b4eeaf9e3879ea2d41819cfc31bc8"
    final override val expectedUpdateSmallHash: String = "3897cf110dbd820286046d51f217836f9182f7de672b9773eac0801d3204f9e2"
    final override val expectedUpdateMediumHash: String = "4a5c4af6aa088b8eb873e2fdc793e35db73c8b5b0aa6cb2caa422be643edba12"

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
