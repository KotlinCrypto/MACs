@SuppressWarnings("module")
module org.kotlincrypto.macs.hmac.sha1 {
    requires kotlin.stdlib;
    requires org.kotlincrypto.hash.sha1;
    requires transitive org.kotlincrypto.macs.hmac;

    exports org.kotlincrypto.macs.hmac.sha1;
}
