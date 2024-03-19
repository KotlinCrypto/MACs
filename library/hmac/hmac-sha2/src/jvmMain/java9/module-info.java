@SuppressWarnings("JavaModuleNaming")
module org.kotlincrypto.macs.hmac.sha2 {
    requires kotlin.stdlib;
    requires org.kotlincrypto.hash.sha2;
    requires transitive org.kotlincrypto.macs.hmac;

    exports org.kotlincrypto.macs.hmac.sha2;
}
