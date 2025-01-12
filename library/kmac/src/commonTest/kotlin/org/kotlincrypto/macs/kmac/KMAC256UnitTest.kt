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

open class KMAC256UnitTest: KmacUnitTest() {
    override fun mac(key: ByteArray): Mac = KMAC256(key)
    override fun xof(key: ByteArray): Updatable = KMAC256.xOf(key)
    override fun read(mac: Updatable, vararg args: ByteArray) {
        (mac as Xof<*>).use {
            args.forEach { read(it) }
        }
    }

    final override val expectedResetSmallHash: String = "2d0ff9bcfb3c5a6afa4fa293fea15cbd24ae94d6b2421ba9fcb143fb466fc8687dbbbd5c4196af14416168822a4a78c073511455986bdee243b08cef03533732"
    final override val expectedResetMediumHash: String = "1d195cbfa591c7af758be9c422abb8d5e36b7cc4a2e7d211f13b5293d617ba751bec68256584436e4938649b854d9433ae239ee6092142f432d4aeb6b39f1f9a"
    final override val expectedResetLargeHash: String = "ee301379894f1a58601dd99faa4399b2bad9eea2d941b140ceffc5c56ffc5d93d33170194cdde28bfdfb1589a10acf42b5eed58ff3849b153971492171d2269f"
    final override val expectedUpdateSmallHash: String = "76e95038c81b74be5f1d04dafe2367b6ed2682ccf3b4bc6256c395ccd0e265a7f43e0544990c37370a54a06b8a4a1921b49c4aa9b798e5e0f771c0635e40f19d"
    final override val expectedUpdateMediumHash: String = "6c5691845f13c5219bd1705bb554fed5b5b2e06dfff3435068565671c54c0751b6aca803a3f9561baf1594366c9b44994512ebda3ac2380bc5bca5949cd2360b"
    final override val expectedXofHash: String = """
        7a7fbcc8216db3b159794062e386ebabca15c567c7374d51114b14635f1d6259
        8fdea4b28c7c6b8a87ed036ca0428bc84d142f49a03d26fb77e9464af07e935b
        421aa53e3689424e1700365472a7ecea25fe1928a4b9cd877a4ea74a8b0e1e45
        021e17c59f6d624480a793cfeba79b8b75ff5a9f65cb4f8fb132d2e71e981d38
        7dbfbd4e2c8d32953a0d919d959b42fb022bdf4a5910caf5a2f33b4a85a28d03
        d39233de4ab9ffc8e23f89b7bf8caa361e37299651f9b03b619bf34b5da56c07
        97eb3f9f7b173d564db4b5f8b1a4d7151432e11febcbb12e400e286f14b1e807
        b533e0f03c09a1ef73335ecc5b54ad1f728f8bae33f89abf0261843de29d643a
        c8b331bfbf17baf290f9361298ee7f99b9006188d42030bfd0372714b9cbaf0b
        b653213d98f039b85948f75bac6bdd049e54297f19e9499eda0570c864e674c8
        233111b60a1d232f393b94c3aa04b4dd95095938fc0772124676d40105331dda
        5d20150c9796df7d246bce954beea26b6ca4b9b283250eabc78a9ac2d683b816
        083b694369b7977f0d5f0e4e368f4aecb178d93dfa25a18b71f2b20a3d9169d4
        5377ecedb3a11bc3fb96228afa3489f799015fae4f8a9047de28c739953ba672
        bafdb500187fc1798a5fad1a62028c46864df5a841d2a82e199dd962197a23ca
        77d175d633b482d0b62aa4560dc9c2101e1c37e5fa03d6e4c2ba5db9b1293ee8
        e725ac9716fa5dd85a22b2dd289c358fd4bde7ce1fed90da6df52c54b49c3ed4
        99a2e931ed7cc292dbec19d30fef13bc79949aef1cb09d265cf5eec9603a8e7e
        f95d108bd1579bfe11c753a3d40bef96124de5bed32eb5635d70f66a428d97b6
        f713760160816d9ec2dc39751bf5f04eda46e5931ef2c456deb6b949249cf203
        948f832258f8313e1ed7a5d50143e3ae1bdaac52e02d41ef825d1669ced963ea
        bc4a1a946e9cfa2097a15115e5df9b4ce0a70c5e754e0d62e9b7856f671b7ce7
        f792270903e67afab377da50919b3bdb8a2cfeaebcc42c8aaad5679a821336e9
        4af7d388be78d4d1d750636039133dd6d208471cdc6fa0aea984ab98d5090683
        69677ad47a3a4b5c308200b10994cb1ae94e6c525891db7664b4474fcc406ba4
        c2d7413b01c956b52e6dc8a5bab93a799314182cc551190ea90d7cbdb871a9ce
        a7383431e7193038fd60467a261d4f4c7d336e6b4f4795578a2f25c3efeb598c
        7ed3f257d136701f247ee4bd9467ceb4eb96f6ba5e8038f23a116f69aa7bde66
        0593c29d6a33580af5ce6cd437b97bbe52933d27dbac8fb7b941d205b5a258b0
        48d3fb499d52d8e0c33b9717d2d6edf6400a5b2e6e0eb85f4dadd6e19991c112
        3b501cac8e95e3089ba768df01504258b5a7e2d845a260139aafeded1a4db279
        ab51df39f7ae40ce2511fecfc982b2638b6508b2810bd2d2796be32ea49f6808
        cc50e26f2aacaab429a618066b990160aa58cdaea87a7ec759d06dd5bbde902b
        7a80a2d20bbe3ab84ac2e3152c4f2542395e6ef1379b65a14eafd309857a813c
        4fb52c49e6fce89ee5d70bc9d9177a8ecb99f28906e599dae0fd04d6d4d8cf45
        9c5b8af0aab3db9b8ce9fb01912521cb6db9f1d7ac2704ffa4a186351d2d45f6
        ffef896ea80013af6380e502abf84f16c8c375c1db996f59ea7b1437ec5b2592
        544e785be06b44836387ffc881f081c40513bc0de688d7f86dc2d974ec36586c
        2414f575a7efc802e8
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
