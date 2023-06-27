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

open class HmacKeccak512UnitTest: MacUnitTest() {
    override fun mac(key: ByteArray): Mac = HmacKeccak512(key)

    final override val expectedResetSmallHash: String = "f0b08d27d63d0fd5dc2d3f32da412bf152b30daf80aa666f6237a43c4762c2640d8b575439f05b04a35c12a45d9d15885d4ed973fcef5426d563c3c9d50fae3d"
    final override val expectedResetMediumHash: String = "a154f50600f4bd2c3bb70e31a11287f72714095bb1b50fffd2e07191d2e4664d687caf332bfaae89f74c7429c4d94d5316a671d9b9240abf3440a60c43aec383"
    final override val expectedResetLargeHash: String = "8052a354339ecbf667247b29b41d525d7bcb3c840f2d183e32817476a51568ed3fab1b3de9e43a5519be235cd4ef3bb63217e4e0cdf776cf37c3a3ee4c3fbf68"
    final override val expectedUpdateSmallHash: String = "e56a9e95a8385fbf94e862e6b1ecbfdb5c815cf839dbd82789d533591019e1fa1a744c3c52aaff974e8c2d6d2d8e7ce4e0601e742569f6273092f5e71075b48e"
    final override val expectedUpdateMediumHash: String = "89a085b2f49c33ac2872df024bbc09416f4b5c18cc2df951baf00c5cde46353110713041a45509606fab1c3aa0dbd6e90aed6c47f4cc43e7b5a5d4f2718271df"

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
