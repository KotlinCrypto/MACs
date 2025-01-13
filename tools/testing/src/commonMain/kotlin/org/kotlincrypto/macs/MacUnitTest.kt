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
@file:Suppress("FunctionName")

package org.kotlincrypto.macs

import io.matthewnelson.encoding.base64.Base64
import io.matthewnelson.encoding.core.Decoder.Companion.decodeToByteArray
import io.matthewnelson.encoding.core.Encoder.Companion.encodeToString
import org.kotlincrypto.core.mac.Mac
import org.kotlincrypto.core.Updatable
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

/**
 * Test abstraction to verify that the Mac implementation is in proper working order.
 * All input utilized is deterministic in order to ensure expected hash values never
 * change.
 *
 * Expected hash values are obtained by an already working implementation of the
 * algorithm under test (e.g. Bouncy Castle provider). Those values must then match
 * when the KotlinCrypto implementation is put under test.
 *
 * To implement this abstraction for a new algorithm:
 *
 * See [org.kotlincrypto.macs.hmac.md.HmacMD5UnitTest] located in `commonTest`. Note that
 * the test class is `open`. This allows it to be inherited from in the `jvmTest` source
 * set, for example the [org.kotlincrypto.macs.hmac.md.HmacMD5JvmUnitTest] test. This
 * ensures that both implementations run under the same test parameters output the same
 * values.
 * */
abstract class MacUnitTest {

    abstract fun mac(key: ByteArray): Mac

    private fun ByteArray.toMaxKeyLen(): ByteArray {
        val max = maxKeyLength ?: return this
        if (this.size < max) return this
        return copyOf(max)
    }

    open val maxKeyLength: Int? = null
    abstract val expectedResetSmallHash: String
    abstract val expectedResetMediumHash: String
    abstract val expectedResetLargeHash: String
    abstract val expectedUpdateSmallHash: String
    abstract val expectedUpdateMediumHash: String

    private val assertExpectedHashes by lazy {
        setOf(
            expectedResetSmallHash,
            expectedResetMediumHash,
            expectedResetLargeHash,
            expectedUpdateSmallHash,
            expectedUpdateMediumHash,
        ).let { assertEquals(5, it.size, "Expected hash values must all be different") }
    }

    open fun givenMac_whenResetSmallKey_thenDoFinalReturnsExpected() {
        assertExpectedHashes
        val mac = mac(KEY_SMALL.toMaxKeyLen())
        val empty = mac.doFinal().encodeToString(TestData.base16)
        updateSmall(mac)
        mac.reset()
        val actual = mac.doFinal().encodeToString(TestData.base16)
        assertEquals(empty, actual)
        assertEquals(expectedResetSmallHash, actual)
    }

    open fun givenMac_whenResetMediumKey_thenDoFinalReturnsExpected() {
        assertExpectedHashes
        val mac = mac(KEY_MEDIUM.toMaxKeyLen())
        val empty = mac.doFinal().encodeToString(TestData.base16)
        updateSmall(mac)
        mac.reset()
        val actual = mac.doFinal().encodeToString(TestData.base16)
        assertEquals(empty, actual)
        assertEquals(expectedResetMediumHash, actual)
    }

    open fun givenMac_whenResetLargeKey_thenDoFinalReturnsExpected() {
        assertExpectedHashes
        val mac = mac(KEY_LARGE.toMaxKeyLen())
        val empty = mac.doFinal().encodeToString(TestData.base16)
        updateSmall(mac)
        mac.reset()
        val actual = mac.doFinal().encodeToString(TestData.base16)
        assertEquals(empty, actual)
        assertEquals(expectedResetLargeHash, actual)
    }

    open fun givenMac_whenUpdatedSmall_thenDoFinalReturnsExpected() {
        assertExpectedHashes
        val mac = mac(KEY_SMALL.toMaxKeyLen())
        mac.doFinal()
        updateSmall(mac)
        val actual = mac.doFinal().encodeToString(TestData.base16)
        assertEquals(expectedUpdateSmallHash, actual)
    }

    open fun givenMac_whenUpdatedMedium_thenDoFinalReturnsExpected() {
        assertExpectedHashes
        val mac = mac(KEY_MEDIUM.toMaxKeyLen())
        mac.doFinal()
        updateMedium(mac)

        val actual = mac.doFinal().encodeToString(TestData.base16)
        assertEquals(expectedUpdateMediumHash, actual)
    }

    open fun givenMac_whenCopied_thenIsDifferentInstance() {
        assertExpectedHashes
        val mac = mac(KEY_SMALL.toMaxKeyLen())
        updateSmall(mac)

        val copy = mac.copy()
        assertNotEquals(copy, mac)
        assertEquals(expectedUpdateSmallHash, copy.doFinal().encodeToString(TestData.base16))
        assertEquals(expectedResetSmallHash, copy.doFinal().encodeToString(TestData.base16))

        updateSmall(copy)
        assertEquals(expectedUpdateSmallHash, copy.doFinal().encodeToString(TestData.base16))
        assertEquals(expectedUpdateSmallHash, mac.doFinal().encodeToString(TestData.base16))
    }

    open fun givenMac_whenDoFinal_thenLengthMatchesOutput() {
        assertExpectedHashes
        assertEquals(mac(KEY_SMALL.toMaxKeyLen()).doFinal().encodeToString(TestData.base16).length, expectedResetSmallHash.length)
    }

    open fun givenMac_whenInstanceResetWithNewKey_thenDoFinalReturnsExpected() {
        assertExpectedHashes
        val mac = mac(KEY_SMALL.toMaxKeyLen())
        updateSmall(mac)
        assertEquals(mac.doFinal().encodeToString(TestData.base16), expectedUpdateSmallHash)
        assertEquals(mac.doFinal().encodeToString(TestData.base16), expectedResetSmallHash)

        mac.reset(KEY_MEDIUM.toMaxKeyLen())
        updateMedium(mac)
        assertEquals(mac.doFinal().encodeToString(TestData.base16), expectedUpdateMediumHash)
        assertEquals(mac.doFinal().encodeToString(TestData.base16), expectedResetMediumHash)
    }

    protected companion object {

        fun updateSmall(mac: Updatable) {
            mac.update(TestData.BYTES_SMALL)
        }

        fun updateMedium(mac: Updatable) {
            mac.update(TestData.BYTES_MEDIUM[3])
            mac.update(TestData.BYTES_MEDIUM)
            mac.update(TestData.BYTES_MEDIUM, 5, 500)
        }

        // 20 bytes
        val KEY_SMALL = """
            1SURpmufJX7RlEnk9zoLo2UwLt8=
        """.trimIndent().decodeToByteArray(Base64.Default)

        // 100 bytes
        val KEY_MEDIUM = """
            HQEhPY467gYDjwh/Vvsq9+XwS2xDf7Jr05L0qiPk5CkDh/h3PCgyjkTo65a+h+wY
            BKaVXSlb2VQRD3Z2LCBCSgcKBd5GID8+U/xDHNFqXUowUWofEkagZYlf16uqkOPr
            CfBVTg==
        """.trimIndent().decodeToByteArray(Base64.Default)

        // 500 bytes
        val KEY_LARGE = """
            abW+KOSnDA3hq1goP/g46Ku3saALq693FpkTv1CxJWCbAoJMGd/oO9woi8VLNsB/
            YtfjrVgk0iT+mCxecG953pr9evhCWq9MuXtogQl3PA5KD5XfHK5ly+LIHg7rhu7l
            /Kq2y9lODRY/OmSXLg/AvU5XDaFDgz4LoJLlW240tC/NGvzp4ZsRYFwLI9gitXRs
            aSAk+fnLxRYMHMTgoPcvDcktmC5yPmaA7QszTbUZvDO4lRsu9Y+eAyff4YGJoC8n
            LlKf13EURPJKnOu+sphHm5tNZxIP3u+Q8oz3Y1kWYOsW9w9XfailYdHZlr7A9RVa
            gPA0VIEoUOITMdBC3H3OS1JcEUHxzi9dKcR6pzmekaA3o0PwQuDKbemKPALn4Pui
            oe7arrFyEGj9S08UgebJ+fqNSuD8cYxmteHLBBM6ePIvd3zm0eNIhSbAU4IKVYvV
            z9NOvKSa41SEKOajo3JjaDxNbUjjaJYSPxYOQcJcJewr7OWR/Yto9G0xoXMGi76o
            BPsqM2QBrN60VdzTg4yLk7GmmGhc6B0NjH+PUP/pD4TVgxzI0Um29DcMZiwOibHr
            JR46yA5FQxsXpj0qHGRXe1mnP0+cKNW6Rc8AJcxZO/PDIPfu2BN1GUjQrhiSCM0j
            Dt/UlpETCDNLlp4aCq8wyQa5CIY=
        """.trimIndent().decodeToByteArray(Base64.Default)
    }
}
