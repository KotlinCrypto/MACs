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
package org.kotlincrypto.macs.siphash

import org.kotlincrypto.core.Algorithm
import org.kotlincrypto.core.InternalKotlinCryptoApi
import org.kotlincrypto.core.mac.Mac
import org.kotlincrypto.endians.LittleEndian
import org.kotlincrypto.endians.LittleEndian.Companion.toLittleEndian
import kotlin.jvm.JvmInline

/**
 * Core abstraction for Siphash
 * Code implementations:
 *  - SIPHASH64
 *  - HALFSIPHASH32
 *
 * @see [Mac]
 * */
public sealed class SipHash : Mac {

    private val engine: Mac.Engine

    @Throws(IllegalArgumentException::class)
    protected constructor(
        secretKey: ByteArray,
    ) : this(
        when (secretKey.size) {
            SIPHASH_KEY_SIZE -> SiphashEngine(secretKey)
            HALF_SIPHASH_KEY_SIZE -> HalfSipHashEngine(secretKey)
            else -> throw IllegalArgumentException(ERROR_MESSAGE)
        }
    )

    @OptIn(InternalKotlinCryptoApi::class)
    @Throws(IllegalArgumentException::class)
    protected constructor(engine: Mac.Engine) : super((engine as Engine).algorithm(), engine) {
        this.engine = engine
    }

    private class SiphashEngine(
        private val key: ByteArray
    ) : Engine(key) {

        private val k0: Long = secretKey.left()
        private val k1: Long = secretKey.right()

        private var v0: Long = 0x736f6d6570736575L xor k0
        private var v1: Long = 0x646f72616e646f6dL xor k1
        private var v2: Long = 0x6c7967656e657261L xor k0
        private var v3: Long = 0x7465646279746573L xor k1

        override fun algorithm(): String = "SipHash"

        override fun copy(): Mac.Engine = SiphashEngine(key)

        override fun reset() {
            super.reset()
            this.v0 = 0x736f6d6570736575L xor k0
            this.v1 = 0x646f72616e646f6dL xor k1
            this.v2 = 0x6c7967656e657261L xor k0
            this.v3 = 0x7465646279746573L xor k1
        }

        override fun doFinal(): ByteArray {
            var m: Long
            val inputs = bytes()
            val iterations = inputs.size / 8

            for (i in 0 until iterations) {
                m = LittleEndian(
                    inputs[0 + i * 8],
                    inputs[1 + i * 8],
                    inputs[2 + i * 8],
                    inputs[3 + i * 8],
                    inputs[4 + i * 8],
                    inputs[5 + i * 8],
                    inputs[6 + i * 8],
                    inputs[7 + i * 8]
                ).toLong()
                processBlock(m)
            }

            m = lastBlock(inputs, iterations)
            processBlock(m)
            finish()
            return digest().toLittleEndian().toByteArray()
        }

        override fun macLength(): Int = SIPHASH_KEY_SIZE

        override fun compress() {
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

        private fun lastBlock(data: ByteArray, iterations: Int): Long {
            var last = data.size.toLong() shl 56
            val off = iterations * 8

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
    }

    private class HalfSipHashEngine(
        private val key: ByteArray
    ) : Engine(key) {

        private val k0: Int = secretKey.leftInt()
        private val k1: Int = secretKey.rightInt()

        private var v0: Int = 0 xor k0
        private var v1: Int = 0 xor k1
        private var v2: Int = 0x6c796765 xor k0
        private var v3: Int = 0x74656462 xor k1


        override fun algorithm(): String = "Half-SipHash"

        override fun copy(): Mac.Engine = HalfSipHashEngine(key)

        override fun reset() {
            super.reset()
            this.v0 = 0 xor k0
            this.v1 = 0 xor k1
            this.v2 = 0x6c796765 xor k0
            this.v3 = 0x74656462 xor k1
        }

        override fun doFinal(): ByteArray {
            var m: Int
            val inputs = bytes()
            val iter = inputs.size / 4

            for (i in 0 until iter) {
                m = LittleEndian(inputs[0 + i * 4], inputs[1 + i * 4], inputs[2 + i * 4], inputs[3 + i * 4]).toInt()
                processBlock(m)
            }

            m = lastBlock(inputs, iter)
            processBlock(m)
            finish()
            return digest().toLittleEndian().toByteArray()
        }

        override fun macLength(): Int = HALF_SIPHASH_KEY_SIZE

        override fun compress() {
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
    }

    @OptIn(InternalKotlinCryptoApi::class)
    private sealed class Engine(key: ByteArray) : Mac.Engine(key), Algorithm {

        abstract fun compress()

        protected val secretKey: SecretSipHashKey = SecretSipHashKey(key)
        private val inputs: MutableList<Byte> = mutableListOf()

        override fun reset() {
            inputs.clear()
        }

        override fun update(input: Byte) {
            inputs.add(input)
        }

        override fun update(input: ByteArray, offset: Int, len: Int) {
            if (offset < 0 || len < 0 || offset > input.size - len) throw IllegalArgumentException("Bad arguments")
            inputs.addAll(offset, input.take(len))
        }

        protected fun bytes(): ByteArray = inputs.toByteArray().copyOf()

        protected fun compressTimes(times: Int) {
            for (i in 0 until times) {
                compress()
            }
        }
    }

    @JvmInline
    private value class SecretSipHashKey(private val bytes: ByteArray) {

        init {
            require(bytes.size == HALF_SIPHASH_KEY_SIZE || bytes.size == SIPHASH_KEY_SIZE) { ERROR_MESSAGE }
        }

        fun left(): Long = LittleEndian(bytes[0], bytes[1], bytes[2], bytes[3], bytes[4], bytes[5], bytes[6], bytes[7]).toLong()
        fun right(): Long = LittleEndian(bytes[8], bytes[9], bytes[10], bytes[11], bytes[12], bytes[13], bytes[14], bytes[15]).toLong()
        fun leftInt(): Int = LittleEndian(bytes[0], bytes[1], bytes[2], bytes[3]).toInt()
        fun rightInt(): Int = LittleEndian(bytes[4], bytes[5], bytes[6], bytes[7]).toInt()
    }

    private companion object {
        private const val SIPHASH_KEY_SIZE = 16
        private const val HALF_SIPHASH_KEY_SIZE = 8
        private const val ERROR_MESSAGE = "SecretKey should be 16 bytes for SIPHASH64 or 8 bytes for HALFSIPHASH32"
    }
}
