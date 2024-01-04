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
@file:Suppress("UnnecessaryOptInAnnotation", "RemoveRedundantQualifierName")

package org.kotlincrypto.macs.siphash

import org.kotlincrypto.core.InternalKotlinCryptoApi
import org.kotlincrypto.core.mac.Mac

/**
 * Core abstraction for SipHash-based Message Authentication
 * Code implementations:
 *  - SipHash
 *  - HalfSipHash
 *
 * @see [Mac]
 * */
public class SipHash : Mac {

    /**
     * Primary constructor for creating a new [SipHash] instance
     *
     * @throws [IllegalArgumentException] if [key] is not 16 or 8 bytes.
     * */
    @Throws(IllegalArgumentException::class)
    public constructor(
        key: ByteArray,
    ) : this(SipHashEngine(key))

    @OptIn(InternalKotlinCryptoApi::class)
    @Throws(IllegalArgumentException::class)
    private constructor(engine: Mac.Engine) : super(
        when (engine.macLength()) {
            16 -> "SIPHASH"
            8 -> "HALF-SIPHASH"
            else -> throw IllegalArgumentException("Key is 16 byte for SipHash or 8 for HalfSipHash")
        }, engine
    )

    protected override fun copy(engineCopy: Engine): Mac = SipHash(engineCopy)

    private class SipHashEngine : Mac.Engine {

        val state: SipHash.State
        var inputs: ByteArray


        @OptIn(InternalKotlinCryptoApi::class)
        constructor(key: ByteArray) : super(key) {
            this.state = when (key.size) {
                16 -> SipHash.SipHashState(SipKey(key))
                8 -> SipHash.HalfSipHashState(SipKey(key))
                else -> throw IllegalArgumentException("Key is 16 byte for SipHash or 8 for HalfSipHash")
            }
            this.inputs = byteArrayOf()
        }

        @OptIn(InternalKotlinCryptoApi::class)
        private constructor(state: State, engine: SipHashEngine) : super(state) {
            this.state = engine.state
            this.inputs = engine.inputs
        }

        override fun update(input: Byte) {
            inputs += input
        }

        override fun update(input: ByteArray, offset: Int, len: Int) {
            inputs = inputs.take(offset).toByteArray() + input + inputs.drop(len).toByteArray()
        }

        override fun doFinal(): ByteArray = state.doFinal(inputs)

        override fun reset() {
            inputs = byteArrayOf()
            state.reset()
        }

        override fun macLength(): Int = state.length()

        override fun copy(): SipHashEngine = SipHashEngine(object : State() {}, this)
    }

    internal interface State {
        fun length(): Int
        fun doFinal(inputs: ByteArray): ByteArray
        fun reset()
    }

    internal class HalfSipHashState internal constructor(key: SipKey) : State {

        private val k0: Int = key.leftInt()
        private val k1: Int = key.rightInt()

        private var v0: Int = 0 xor k0
        private var v1: Int = 0 xor k1
        private var v2: Int = 0x6c796765 xor k0
        private var v3: Int = 0x74656462 xor k1

        override fun reset() {
            v0 = 0 xor k0
            v1 = 0 xor k1
            v2 = 0x6c796765 xor k0
            v3 = 0x74656462 xor k1
        }

        override fun doFinal(inputs: ByteArray): ByteArray {
            var m: Int
            val iter = inputs.size / 4

            for (i in 0 until iter) {
                m = inputs.convertToInt(i * 4)
                processBlock(m)
            }

            m = lastBlock(inputs, iter)
            processBlock(m)
            finish()
            val digest = digest()

            return byteArrayOf(
                (digest shr 0).toByte(),
                (digest shr 8).toByte(),
                (digest shr 16).toByte(),
                (digest shr 24).toByte(),
            )
        }

        private fun compress() {
            v0 += v1
            v1 = rotateLeft(v1, 5)
            v1 = v1 xor v0
            v0 = rotateLeft(v0, 16)
            v2 += v3
            v3 = rotateLeft(v3, 8)
            v3 = v3 xor v2
            v0 += v3
            v3 = rotateLeft(v3, 7)
            v3 = v3 xor v0
            v2 += v1
            v1 = rotateLeft(v1, 13)
            v1 = v1 xor v2
            v2 = rotateLeft(v2, 16)
        }

        private fun rotateLeft(value: Int, shift: Int): Int = value shl shift or (value ushr 32 - shift)

        private fun lastBlock(data: ByteArray, iter: Int): Int {
            var last: Int = data.size shl 24
            val off = iter * 4

            when (data.size % 4) {
                3 -> {
                    last = last or (data[off + 2].toUByte().toInt() shl 16)
                    last = last or (data[off + 1].toUByte().toInt() shl 8)
                    last = last or data[off].toUByte().toInt()
                }

                2 -> {
                    last = last or (data[off + 1].toUByte().toInt() shl 8)
                    last = last or data[off].toUByte().toInt()
                }

                1 -> last = last or data[off].toUByte().toInt()
                0 -> {}
            }
            return last
        }

        private fun compressTimes(times: Int) {
            for (i in 0 until times) {
                compress()
            }
        }

        private fun processBlock(m: Int) {
            v3 = v3 xor m
            compressTimes(2)
            v0 = v0 xor m
        }

        private fun finish() {
            v2 = v2 xor 0xff
            compressTimes(4)
        }

        private fun digest(): Int = v1 xor v3

        override fun length(): Int = 8
    }

    internal class SipHashState internal constructor(
        key: SipKey
    ) : State {
        private val k0: Long = key.left()
        private val k1: Long = key.right()

        private var v0: Long = 0x736f6d6570736575L xor k0
        private var v1: Long = 0x646f72616e646f6dL xor k1
        private var v2: Long = 0x6c7967656e657261L xor k0
        private var v3: Long = 0x7465646279746573L xor k1

        override fun reset() {
            v0 = 0x736f6d6570736575L xor k0
            v1 = 0x646f72616e646f6dL xor k1
            v2 = 0x6c7967656e657261L xor k0
            v3 = 0x7465646279746573L xor k1
        }

        override fun doFinal(inputs: ByteArray): ByteArray {
            var m: Long
            val iter = inputs.size / 8

            for (i in 0 until iter) {
                m = inputs.convertToLong(i * 8)
                processBlock(m)
            }

            m = lastBlock(inputs, iter)
            processBlock(m)
            finish()
            val digest = digest()

            return byteArrayOf(
                (digest shr 0).toByte(),
                (digest shr 8).toByte(),
                (digest shr 16).toByte(),
                (digest shr 24).toByte(),
                (digest shr 32).toByte(),
                (digest shr 40).toByte(),
                (digest shr 48).toByte(),
                (digest shr 56).toByte(),
            )
        }

        private fun compress() {
            v0 += v1
            v1 = rotateLeft(v1, 13)
            v1 = v1 xor v0
            v0 = rotateLeft(v0, 32)
            v2 += v3
            v3 = rotateLeft(v3, 16)
            v3 = v3 xor v2
            v0 += v3
            v3 = rotateLeft(v3, 21)
            v3 = v3 xor v0
            v2 += v1
            v1 = rotateLeft(v1, 17)
            v1 = v1 xor v2
            v2 = rotateLeft(v2, 32)
        }

        private fun rotateLeft(value: Long, shift: Int): Long = value shl shift or (value ushr 64 - shift)

        private fun lastBlock(data: ByteArray, iter: Int): Long {
            var last = data.size.toLong() shl 56
            val off = iter * 8

            when (data.size % 8) {
                7 -> {
                    last = last or (data[off + 6].toLong() shl 48)
                    last = last or (data[off + 5].toLong() shl 40)
                    last = last or (data[off + 4].toLong() shl 32)
                    last = last or (data[off + 3].toLong() shl 24)
                    last = last or (data[off + 2].toLong() shl 16)
                    last = last or (data[off + 1].toLong() shl 8)
                    last = last or data[off].toLong()
                }

                6 -> {
                    last = last or (data[off + 5].toLong() shl 40)
                    last = last or (data[off + 4].toLong() shl 32)
                    last = last or (data[off + 3].toLong() shl 24)
                    last = last or (data[off + 2].toLong() shl 16)
                    last = last or (data[off + 1].toLong() shl 8)
                    last = last or data[off].toLong()
                }

                5 -> {
                    last = last or (data[off + 4].toLong() shl 32)
                    last = last or (data[off + 3].toLong() shl 24)
                    last = last or (data[off + 2].toLong() shl 16)
                    last = last or (data[off + 1].toLong() shl 8)
                    last = last or data[off].toLong()
                }

                4 -> {
                    last = last or (data[off + 3].toLong() shl 24)
                    last = last or (data[off + 2].toLong() shl 16)
                    last = last or (data[off + 1].toLong() shl 8)
                    last = last or data[off].toLong()
                }

                3 -> {
                    last = last or (data[off + 2].toLong() shl 16)
                    last = last or (data[off + 1].toLong() shl 8)
                    last = last or data[off].toLong()
                }

                2 -> {
                    last = last or (data[off + 1].toLong() shl 8)
                    last = last or data[off].toLong()
                }

                1 -> last = last or data[off].toLong()
                0 -> {}
                else -> throw IllegalStateException("Unexpected offset: $off")
            }
            return last
        }

        private fun compressTimes(times: Int) {
            for (i in 0 until times) {
                compress()
            }
        }

        private fun processBlock(m: Long) {
            v3 = v3 xor m
            compressTimes(2)
            v0 = v0 xor m
        }

        private fun finish() {
            v2 = v2 xor 0xffL
            compressTimes(4)
        }

        private fun digest(): Long = v0 xor v1 xor v2 xor v3

        override fun length(): Int = 16
    }

    internal class SipKey(
        private val bytes: ByteArray,
    ) {
        init {
            require(bytes.size == 8 || bytes.size == 16) { "HalfSipHash key must be 8 bytes\nSipHash key must be 16 bytes" }
        }

        public fun left(): Long = bytes.convertToLong(0)
        public fun leftInt(): Int = bytes.convertToInt(0)
        public fun right(): Long = bytes.convertToLong(8)
        public fun rightInt(): Int = bytes.convertToInt(4)
    }
}

internal fun ByteArray.convertToLong(offset: Int): Long {
    var m: Long = 0
    for (i in 0..<8) {
        m = m or (get(i + offset).toLong() and 0xffL shl 8 * i)
    }
    return m
}

internal fun ByteArray.convertToInt(offset: Int): Int {
    var m: Int = 0
    for (i in 0..<4) {
        m = m or (get(i + offset).toInt() and 0xff shl 8 * i)
    }
    return m
}
