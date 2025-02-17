/*
 * Copyright (c) 2024 KotlinCrypto
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
@file:Suppress("ClassName", "SpellCheckingInspection", "unused")

package org.kotlincrypto.macs.benchmarks

import kotlinx.benchmark.*
import org.kotlincrypto.core.mac.Mac
import org.kotlincrypto.macs.hmac.sha3.HmacSHA3_256
import org.kotlincrypto.macs.hmac.sha3.HmacSHA3_512

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(BenchmarkTimeUnit.NANOSECONDS)
@Warmup(iterations = ITERATIONS, time = TIME_WARMUP)
@Measurement(iterations = ITERATIONS, time = TIME_MEASURE)
open class HmacSHA3_256Benchmark: MacBenchmarkBase() {
    override val m: Mac = HmacSHA3_256(key)
}

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(BenchmarkTimeUnit.NANOSECONDS)
@Warmup(iterations = ITERATIONS, time = TIME_WARMUP)
@Measurement(iterations = ITERATIONS, time = TIME_MEASURE)
open class HmacSHA3_512Benchmark: MacBenchmarkBase() {
    override val m: Mac = HmacSHA3_512(key)
}
