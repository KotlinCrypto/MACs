module org.kotlincrypto.macs.hmac {
    requires kotlin.stdlib;
    requires org.kotlincrypto.core.digest;
    requires transitive org.kotlincrypto.core.mac;

    exports org.kotlincrypto.macs.hmac;
}
