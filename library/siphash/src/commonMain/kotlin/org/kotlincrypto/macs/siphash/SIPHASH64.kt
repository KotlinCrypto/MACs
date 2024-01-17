package org.kotlincrypto.macs.siphash

import org.kotlincrypto.core.mac.Mac

/**
 * SIPHASH64 Message Authentication
 * Created by Jean-Philippe Aumasson and Daniel J. Bernstein in 2012
 *
 * @see [Mac]
 */
public class SIPHASH64 : SipHash {

    /**
     * Creates a new [SIPHASH64] [Mac] instance with:
     * - 2 compression rounds
     * - 4 finalization rounds
     *
     * It returns a 64-bit tag
     *
     * @throws [IllegalArgumentException] if [secretKey] is not 16 bytes.
     */
    public constructor(secretKey: ByteArray) : super(secretKey)

    private constructor(engine: Mac.Engine) : super(engine)

    override fun copy(engineCopy: Mac.Engine): Mac = SIPHASH64(engineCopy)
}
