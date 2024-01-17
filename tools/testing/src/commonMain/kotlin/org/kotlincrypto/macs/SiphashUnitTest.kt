package org.kotlincrypto.macs

import io.matthewnelson.encoding.core.Encoder.Companion.encodeToString
import org.kotlincrypto.core.Updatable
import org.kotlincrypto.core.mac.Mac
import org.kotlincrypto.macs.TestData
import kotlin.test.*

@OptIn(ExperimentalUnsignedTypes::class)
abstract class SiphashUnitTest {

    abstract val validKey: ByteArray
    abstract fun mac(key: ByteArray): Mac

    abstract val expectedAlgorithm: String
    abstract val expectedHashes: List<UByteArray>
    abstract val expectedResetHash: String
    abstract val expectedUpdateSmallHash: String
    abstract val expectedUpdateMediumHash: String


    open fun givenInvalidKey_thenErrorIsThrown(key: ByteArray) {
        try {
            mac(key)
            fail("Should throw error because Key should be 8 or 16")
        } catch (invalidKey: IllegalArgumentException) {
            assertTrue(true, "Should have thrown Exception")
        }
    }

    open fun givenMac_whenHashingInputs_thenDoFinalReturnsExpected() {
        val mac = mac(validKey)
        expectedHashes.forEachIndexed { index, hash ->
            val data = ByteArray(index) { it.toByte() }
            val result = mac.doFinal(data)
            assertContentEquals(hash.toByteArray(), result, "Diff: $index")
        }
    }

    open fun givenMac_whenResetKey_thenDoFinalReturnsExpected() {
        val mac = mac(validKey)
        val empty = mac.doFinal().encodeToString(TestData.base16)
        updateSmall(mac)
        mac.reset()
        val actual = mac.doFinal().encodeToString(TestData.base16)
        assertEquals(empty, actual)
        assertEquals(expectedResetHash, actual)
    }

    open fun givenMac_whenUpdatedSmall_thenDoFinalReturnsExpected() {
        val mac = mac(validKey)
        mac.doFinal()
        updateSmall(mac)
        val actual = mac.doFinal().encodeToString(TestData.base16)
        assertEquals(expectedUpdateSmallHash, actual)
    }

    open fun givenMac_whenUpdatedMedium_thenDoFinalReturnsExpected() {
        val mac = mac(validKey)
        mac.doFinal()
        updateMedium(mac)

        val actual = mac.doFinal().encodeToString(TestData.base16)
        assertEquals(expectedUpdateMediumHash, actual)
    }

    open fun givenMac_whenCopied_thenIsDifferentInstance() {
        val mac = mac(validKey)
        updateSmall(mac)
        val copy = mac.copy()
        copy.reset()

        assertEquals(expectedResetHash, copy.doFinal().encodeToString(TestData.base16))
        assertEquals(expectedUpdateSmallHash, mac.doFinal().encodeToString(TestData.base16))
    }

    open fun givenMac_whenDoFinal_thenLengthMatchesOutput() {
        assertEquals(mac(validKey).doFinal().encodeToString(TestData.base16).length, expectedResetHash.length)
    }

    open fun givenMac_thenExpectedAlgorithmIsUsed() {
        assertEquals(expectedAlgorithm, mac(validKey).algorithm())
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
    }
}