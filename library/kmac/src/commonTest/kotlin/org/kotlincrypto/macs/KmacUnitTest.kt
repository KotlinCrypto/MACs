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

import io.matthewnelson.encoding.builders.Base16
import io.matthewnelson.encoding.core.Encoder.Companion.encodeToString
import org.kotlincrypto.core.Updatable
import kotlin.test.Test
import kotlin.test.assertEquals

abstract class KmacUnitTest: MacUnitTest() {

    abstract fun xof(key: ByteArray): Updatable

    abstract fun read(mac: Updatable, vararg args: ByteArray)

    abstract val expectedXofHash: String

    @Test
    fun givenKMACXof_whenUpdateMedium_thenReadReturnsExpected() {
        val mac = xof(KEY_MEDIUM)
        updateMedium(mac)

        val reads = Array(50) { i -> ByteArray(i) }
        read(mac, *reads)
        var bytes = reads.first()
        for (i in 1 until reads.size) {
            // Sum them all up
            bytes += reads[i]
        }

        val actual = bytes.encodeToString(base16)
        assertEquals(expectedXofHash, actual)
    }

    private companion object {
        private val base16 = Base16 {
            encodeToLowercase = true
            lineBreakInterval = 64
        }
    }
}
