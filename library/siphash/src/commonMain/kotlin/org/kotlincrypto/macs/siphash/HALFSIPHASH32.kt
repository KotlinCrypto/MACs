package org.kotlincrypto.macs.siphash

import org.kotlincrypto.core.mac.Mac

/**
 * HALFSIPHASH32 Message Authentication
 * Created by Jean-Philippe Aumasson and Daniel J. Bernstein in 2012
 *
 * @see [Mac]
 */
public class HALFSIPHASH32 : SipHash {

    /**
     * Creates a new [HALFSIPHASH32] [Mac] instance with:
     * - 2 compression rounds
     * - 4 finalization rounds
     *
     * It returns a 32-bit tag
     *
     * @throws [IllegalArgumentException] if [secretKey] is not 16 bytes.
    */
    public constructor(secretKey: ByteArray) : super(secretKey)

    private constructor(engine: Mac.Engine) : super(engine)

    override fun copy(engineCopy: Mac.Engine): Mac = HALFSIPHASH32(engineCopy)
}
