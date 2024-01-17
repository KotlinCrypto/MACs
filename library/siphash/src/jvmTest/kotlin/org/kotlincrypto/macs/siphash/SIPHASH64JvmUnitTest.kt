package org.kotlincrypto.macs.siphash

import org.junit.Test
import org.kotlincrypto.core.mac.Mac
import org.kotlincrypto.macs.TestJvmMac

class SIPHASH64JvmUnitTest : SIPHASH64UnitTest() {
    override fun mac(key: ByteArray): Mac = TestJvmMac(super.mac(key).algorithm(), key)

    @Test
    override fun givenMac_whenCopied_thenIsDifferentInstance() {
        // Clone not supported with bouncy castle
//        super.givenMac_whenCopied_thenIsDifferentInstance()
    }
}