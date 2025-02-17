/*
 * Copyright (c) 2023 KotlinCrypto
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
@file:Suppress("SpellCheckingInspection")

package org.kotlincrypto.macs.hmac

import org.kotlincrypto.core.digest.Digest
import org.kotlincrypto.core.mac.Mac
import org.kotlincrypto.error.InvalidKeyException
import org.kotlincrypto.error.InvalidParameterException
import kotlin.experimental.xor

/**
 * Core abstraction for Hash-based Message Authentication Code implementations.
 * */
public abstract class Hmac: Mac {

    /**
     * Primary constructor for creating a new [Hmac] instance
     *
     * **NOTE:** [digest] must be a newly created instance.
     *
     * @throws [InvalidKeyException] if [key] size is less than 1
     * @throws [InvalidParameterException] if [algorithm] is blank
     * */
    @Throws(InvalidKeyException::class, InvalidParameterException::class)
    protected constructor(
        key: ByteArray,
        algorithm: String,
        digest: Digest,
    ): super(algorithm, Engine(key, digest))

    /**
     * Secondary constructor for implementing [copy].
     *
     * Implementors of [Hmac] should have a private secondary constructor
     * that is utilized by its [copy] implementation.
     *
     * e.g.
     *
     *     public class HmacSHA256: Hmac {
     *
     *         // ...
     *
     *         private constructor(other: HmacSHA256): super(other) {
     *             // Copy implementation details...
     *         }
     *
     *         // Notice the updated return type
     *         public override fun copy(): HmacSHA256 = HmacSHA256(this)
     *
     *         // ...
     *     }
     * */
    protected constructor(other: Hmac): super(other)

    public abstract override fun copy(): Hmac

    private class Engine: Mac.Engine {

        private val iKey: ByteArray
        private val oKey: ByteArray
        private val digest: Digest

        @Throws(InvalidKeyException::class)
        constructor(key: ByteArray, digest: Digest): super(key, resetOnDoFinal = false) {
            this.digest = digest
            this.iKey = ByteArray(digest.blockSize())
            this.oKey = ByteArray(digest.blockSize())
            this.digest.doInitialization(key = key, iKey = iKey, oKey = oKey, needsReset = false)
        }

        private constructor(other: Engine): super(other) {
            this.iKey = other.iKey.copyOf()
            this.oKey = other.oKey.copyOf()
            this.digest = other.digest.copy()
        }

        override fun copy(): Engine = Engine(this)

        override fun update(input: Byte) { digest.update(input) }
        override fun update(input: ByteArray, offset: Int, len: Int) { digest.update(input, offset, len) }

        override fun doFinal(): ByteArray {
            val final = ByteArray(digest.digestLength())
            doFinalInto(final, 0)
            return final
        }

        override fun doFinalInto(dest: ByteArray, destOffset: Int) {
            val inner = digest.digest()
            digest.update(oKey)
            digest.update(inner)
            digest.digestInto(dest, destOffset)
            digest.update(iKey)
        }

        override fun reset() {
            digest.reset()
            digest.update(iKey)
        }

        override fun reset(newKey: ByteArray) {
            digest.doInitialization(key = newKey, iKey = iKey, oKey = oKey, needsReset = true)
        }

        override fun macLength(): Int = digest.digestLength()

        @Suppress("NOTHING_TO_INLINE", "KotlinRedundantDiagnosticSuppress")
        private inline fun Digest.doInitialization(
            key: ByteArray,
            iKey: ByteArray,
            oKey: ByteArray,
            needsReset: Boolean,
        ) {
            val blockSize = blockSize()
            if (needsReset) reset()

            val sizedKey = if (key.size > blockSize) {
                val keyHash = digest(key)
                keyHash.copyOf(blockSize).also { keyHash.fill(0) }
            } else {
                // Even if provided key is the correct size, still
                // create a copy so sizedKey can always be blanked
                // after deriving iKey and oKey.
                //
                // If the provided key is undersized, it will be
                // padded with 0's.
                key.copyOf(blockSize)
            }

            for (i in 0..<blockSize) {
                iKey[i] = sizedKey[i] xor I_PAD
                oKey[i] = sizedKey[i] xor O_PAD
            }

            sizedKey.fill(0)
            update(iKey)
        }

        private companion object {
            private const val I_PAD: Byte = 0x36
            private const val O_PAD: Byte = 0x5C
        }
    }
}
