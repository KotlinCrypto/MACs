module org.kotlincrypto.macs.hmac.md {
    requires kotlin.stdlib;
    requires org.kotlincrypto.hash.md;
    requires transitive org.kotlincrypto.macs.hmac;

    exports org.kotlincrypto.macs.hmac.md;
}
