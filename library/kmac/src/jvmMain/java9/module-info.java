module org.kotlincrypto.macs.kmac {
    requires kotlin.stdlib;
    requires org.kotlincrypto.hash.sha3;
    requires transitive org.kotlincrypto.core.mac;
    requires transitive org.kotlincrypto.core.xof;

    exports org.kotlincrypto.macs.kmac;
}
