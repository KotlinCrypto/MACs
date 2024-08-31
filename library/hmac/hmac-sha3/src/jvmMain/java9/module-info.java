@SuppressWarnings("module")
module org.kotlincrypto.macs.hmac.sha3 {
    requires kotlin.stdlib;
    requires org.kotlincrypto.hash.sha3;
    requires transitive org.kotlincrypto.macs.hmac;

    exports org.kotlincrypto.macs.hmac.sha3;
}
