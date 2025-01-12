/*
 * Copyright (c) 2024 Matthew Nelson
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
@file:Suppress("unused")

package org.kotlincrypto.macs.benchmarks

import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.Setup
import org.kotlincrypto.core.mac.Mac
import kotlin.random.Random

const val ITERATIONS = 5
const val TIME_WARMUP = 2
const val TIME_MEASURE = 4

abstract class MacBenchmarkBase {

    protected val key = Random.Default.nextBytes(50)

    protected abstract val m: Mac
    private val bytes by lazy { Random.Default.nextBytes((m.macLength() * 2) + 10) }

    @Setup
    fun setup() { m.update(bytes, 0, m.macLength()) }

    @Benchmark
    fun doFinal() = m.doFinal(bytes)
}
