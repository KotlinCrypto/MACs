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
open class HmacSHA3_384UnitTest: MacUnitTest() {
    override fun mac(key: ByteArray): Mac = HmacSHA3_384(key)

    final override val expectedResetSmallHash: String = "470a7aa3ea25cd506cb07c684371c1e0e63e4d9721f94d07480751017952982f377eaa573cfdc92804abad2e998fe181"
    final override val expectedResetMediumHash: String = "054e77f08b328d49ea14e8077911f6b417d01c92598caa8409ef732d8f3d0bfdf09d447bdb2238937f74019b1aa060ab"
    final override val expectedResetLargeHash: String = "2bf1ccc2ca4eb4fcfe3b74753546bce2618a0655077e949726a923386915cf1db995956cec59bfb997a5d60a92271a2b"
    final override val expectedUpdateSmallHash: String = "1d2eca096581c29a1f804c86297502221e71f04d8d572495615abc72dd845acbb82266b0ac488071c4d603e0d9607435"
    final override val expectedUpdateMediumHash: String = "c4f46b559d9cb5196fd7ef7338bab00de624de2ad05117faa7c1061898839715999ed2a7c179d03fbbd728e170b76951"

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
