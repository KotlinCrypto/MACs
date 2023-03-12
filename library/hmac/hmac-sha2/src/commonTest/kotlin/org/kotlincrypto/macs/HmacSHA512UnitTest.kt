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

open class HmacSHA512UnitTest: MacUnitTest() {
    override fun mac(key: ByteArray): Mac = HmacSHA512(key)

    final override val expectedResetSmallHash: String = "d21c3e4d7d75cede2fc2507697847d0f8b20d76863758fd4c57ce76fc39b1c2bc8f48e635448e1d26c6365f0ae6ceed37e8b2dcb0813394b3d63e16a546ed578"
    final override val expectedResetMediumHash: String = "cb1f5b16b684cef7ac0b073a35ca26201d8c573d27ffebcd63c16afe5ed21918ae1b32439ad862e4617058ce8fa93ee744f35afb2390cb386bed0e371b16b833"
    final override val expectedResetLargeHash: String = "406d191d785a3419a66f38344cc567824eebcaad6a8c53cd461b92814c909dff610e4bf398e9a1461a92cfc1b107dbe7c7f0880b649eb8398d7dfab4b0dbaab4"
    final override val expectedUpdateSmallHash: String = "43cd3fe09d77f4ede22b25030edd246cde22f30e4ac4aa75c413bd0e255e5baebd42f463dbf4000b32556faf73e4f3885127702757a8d08c59b6c0cb83c52056"
    final override val expectedUpdateMediumHash: String = "aa4c727c998f8b82f005bd60dc32dbc638adf2485e1902ab90b0f7a6c1c057fea43c04e24bcfc0a2cad777c7a5058ab502aeafab0ca6c35b6d31236c88ce91af"

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
