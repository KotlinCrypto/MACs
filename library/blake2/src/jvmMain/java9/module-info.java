@SuppressWarnings("module")
module org.kotlincrypto.macs.blake2 {
    requires kotlin.stdlib;
    requires org.kotlincrypto.hash.blake2;
    requires transitive org.kotlincrypto.core.mac;

    exports org.kotlincrypto.macs.blake2;
}
