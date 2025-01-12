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
package org.kotlincrypto.macs.kmac

import org.kotlincrypto.core.Updatable
import org.kotlincrypto.core.mac.Mac
import org.kotlincrypto.core.xof.Xof
import kotlin.test.Test

open class KMAC128UnitTest: KmacUnitTest() {
    override fun mac(key: ByteArray): Mac = KMAC128(key)
    override fun xof(key: ByteArray): Updatable = KMAC128.xOf(key)
    override fun read(mac: Updatable, vararg args: ByteArray) {
        (mac as Xof<*>).use {
            args.forEach { read(it) }
        }
    }

    final override val expectedResetSmallHash: String = "149fbd170acf039146689ca60c01466c8a07c5fa583624fcad89268a36e0415c"
    final override val expectedResetMediumHash: String = "f79b45c76cf87f4a70e6c25470b5bf8ca7f1010872c54a340dd8467f06058ba8"
    final override val expectedResetLargeHash: String = "950ac49e521f115af4ff8394f9a2cffae6aba62fbd9b4c94a03ab6c05ab490b0"
    final override val expectedUpdateSmallHash: String = "725dad6073bbafe5101a8884a11d03e95f86a438bb810b89aaa4486e8c4338f6"
    final override val expectedUpdateMediumHash: String = "3272e43a7ebd9ef6b51358bd687cd8df352acebe5c25343d5694bf8ed1462b98"
    final override val expectedXofHash: String = """
        c49d118f524e07717cb86d6766f42ad71ec772032d1839d9ba779dd4b1d92d14
        6622348432d0ed652e459510fba02ca92e86a5e248149c76c6b43079f5021778
        b241f4e38af4feb0f6f99049d44d5f2852f7c71fc800dd537edb8dddcf89a7ef
        2b2673b02e7ceb770f211811a973124b3ddd6ed5f4e7eb4d43596028144fb306
        9d6ddeaa55bd3db8cfa94374f37786a7a01a6f945904b53b8b2008256f9e05f1
        a5008ee8754a3046d0e9ba106e2f89855d7d4352b976f8c2a9ac702f74d1da5c
        2d928b953a2485fe91fd03c5a7f56ce82441aceab8a6b26b461f2e440fc21e75
        83c00987df89d7da70bc5b66e95d99eaa30f331ea052c997da5e8cae13a81e48
        3101ea30dc22f34ea2eda5a6f08a135b16aaacb7c4970ededdf604b22d411459
        a866c60bb26d36b759c536bc0a44f73b7c94f161c34b69396bbb5b8c7cf5c7f7
        c359cdc8b527580b7f10c83226532ebc8ccffbef4d6b6e595a5f00699d77dc0f
        d15e1cee26a62d0e1510207c8668264e7e3ad78dff9ebf32c7e485c63edb4dfd
        4c0a88636877be8860db71390dbcad9d5204afb6e02ec8d409f939972fadd802
        706a1c0151017bb8c9adf2028b99c555809407389d5e48fec7f4427325a2d06a
        b1b9806f0a0b0a89dcafa9b825ecd0e9fe5e226f093e313ade627ff64940760a
        ac04ad8ad972f451775bd412c3874bae8b27036ad0d5ed6bd1fd817ad602a1d2
        e22f1c98a5f1b1ec39607bb0b50b0ecc0b847b9f1859e94840533c8b7640c126
        c43212e8214404b1da2e74efc8ea9225d5b0ae602fcc019846856e23f26b4595
        0d98afcdc8239de14b4ed68d4065d9bcf518339652281bfdab77996ef43954dc
        92feb403faf5e2f0ecae01b0b335dae516b1d9ae6764841f08d32f5fef436d21
        56baf1469e6f9632fe55ef36a7496cce4d4bd63eba9b3dc171be52bc5834f0d8
        c8350bc1c9f1d17c6a9a66d34e1f632ccc72af6e70780d2404d2587d584f62aa
        0389f18a33fe1a736bffa5810d3d9b0db6283615b362f8c3ccbd88ccf4c82ddf
        0c4d0b632ff74221f4891004a67910172d7fab21ac879e8f06cb533a38659a82
        28548fde65e802cdf200c6d88a4e1163883ff39afc070274845099d49b9e59bf
        3a667c8c44f7482efb2c3adc2db6adc266f19cb9dc30e229003f1016742640af
        fa31e8a08b22e77a6d51a31b1ec01cf5ee2d0b65352f7db36279679915898e68
        feea99ad2bd864498246ad3570e1113f353607f334fa8047e7b37764bc9614b2
        f522f8fe40fad4b2666dd4165fa6a400fa7d9c4e45247bcfdb679d0fb301cc92
        ccd9cceb7f2f2021c818398f23150e9aa61b761260b88f2ddfe45d049fa3a15c
        59230442db602f791ac07c6a8c31fc18c592b69b85864f5e7c4574c6facbf345
        203e6cf286533998712fa154dfc4348017be4d966b4704e9355425662a9c8e93
        ef79f0ac18f9e1eac10b31abee9b46cd5c9386bb520341311db09f6f1ab17e01
        f91e3b433bd6f16e1c71b85b4345408d642bbc9fbf19f0af2382773749e4e46b
        f485bbc7a14e9e538b6fa7d0d6757d9644b4c0df6a2dab1b51ca0df9cfe16e29
        26261bbe02afa036bb1501a1c1bfdbbec733f4b9053fada34e4dac79c72b9766
        71088d0d4994b5a911b54c050521a260203abd38bb2d89523f327ba2078a2627
        4f7e53a5b1a7cc20cde19308d3f2fdea07973682872a8111a89e2de356af7afc
        450a071356ffd697f3
    """.trimIndent()

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

    @Test
    final override fun givenMac_whenInstanceResetWithNewKey_thenDoFinalReturnsExpected() {
        super.givenMac_whenInstanceResetWithNewKey_thenDoFinalReturnsExpected()
    }
}
