/*
 * Copyright (c) 2025 Matthew Nelson
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
@file:JvmName("HmacSHA512tKt")
@file:Suppress("FunctionName", "SpellCheckingInspection")

package org.kotlincrypto.macs.hmac.sha2

import kotlin.jvm.JvmName


/** @suppress */
@Deprecated("Use HmacSHA512t directly", ReplaceWith("HmacSHA512t(key, 224)", "org.kotlincrypto.macs.hmac.sha2.HmacSHA512t"))
public fun HmacSHA512_224(key: ByteArray): HmacSHA512t = HmacSHA512t(key, 224)

/** @suppress */
@Deprecated("Use HmacSHA512t directly", ReplaceWith("HmacSHA512t(key, 256)", "org.kotlincrypto.macs.hmac.sha2.HmacSHA512t"))
public fun HmacSHA512_256(key: ByteArray): HmacSHA512t = HmacSHA512t(key, 256)
