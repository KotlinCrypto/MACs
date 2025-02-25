# MACs
[![badge-license]][url-license]
[![badge-latest-release]][url-latest-release]

[![badge-kotlin]][url-kotlin]
[![badge-core]][url-core]
[![badge-hash]][url-hash]

![badge-platform-android]
![badge-platform-jvm]
![badge-platform-js]
![badge-platform-js-node]
![badge-platform-wasm]
![badge-platform-linux]
![badge-platform-macos]
![badge-platform-ios]
![badge-platform-tvos]
![badge-platform-watchos]
![badge-platform-windows]
![badge-support-android-native]
![badge-support-apple-silicon]
![badge-support-js-ir]
![badge-support-linux-arm]

Message Authentication Code algorithms for Kotlin Multiplatform

### Modules

 - [hmac](library/hmac/hmac/README.md)
 - [hmac-md](library/hmac/hmac-md/README.md)
 - [hmac-sha1](library/hmac/hmac-sha1/README.md)
 - [hmac-sha2](library/hmac/hmac-sha2/README.md)
 - [hmac-sha3](library/hmac/hmac-sha3/README.md)
 - [blake2](library/blake2/README.md)
 - [kmac](library/kmac/README.md)

### API Docs

 - [https://macs.kotlincrypto.org][url-docs]

### Get Started

The best way to keep `KotlinCrypto` dependencies up to date is by using the 
[version-catalog][url-version-catalog]. Alternatively, you can use the BOM as 
shown below.

<!-- TAG_VERSION -->

```kotlin
// build.gradle.kts
dependencies {
    // define the BOM and its version
    implementation(platform("org.kotlincrypto.macs:bom:0.7.0"))

    // define artifacts without version
    
    // HmacMD5
    implementation("org.kotlincrypto.macs:hmac-md")
    
    // HmacSHA1
    implementation("org.kotlincrypto.macs:hmac-sha1")
    
    // HmacSHA224, HmacSHA256, HmacSHA384, HmacSHA512
    // HmacSHA512/t, HmacSHA512/224, HmacSHA512/256
    implementation("org.kotlincrypto.macs:hmac-sha2")
    
    // HmacKeccak224, HmacKeccak256, HmacKeccak384, HmacKeccak512
    // HmacSHA3-224, HmacSHA3-256, HmacSHA3-384, HmacSHA3-512
    implementation("org.kotlincrypto.macs:hmac-sha3")

    // KMAC128, KMAC256
    implementation("org.kotlincrypto.macs:kmac")

    // BLAKE2b, BLAKE2s
    implementation("org.kotlincrypto.macs:blake2")
}
```

<!-- TAG_VERSION -->
[badge-latest-release]: https://img.shields.io/badge/latest--release-0.7.0-blue.svg?style=flat
[badge-license]: https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat

<!-- TAG_DEPENDENCIES -->
[badge-kotlin]: https://img.shields.io/badge/kotlin-2.1.10-blue.svg?logo=kotlin
[badge-core]: https://img.shields.io/badge/kotlincrypto.core-0.7.0-blue.svg
[badge-hash]: https://img.shields.io/badge/kotlincrypto.hash-0.7.0-blue.svg

<!-- TAG_PLATFORMS -->
[badge-platform-android]: http://img.shields.io/badge/-android-6EDB8D.svg?style=flat
[badge-platform-jvm]: http://img.shields.io/badge/-jvm-DB413D.svg?style=flat
[badge-platform-js]: http://img.shields.io/badge/-js-F8DB5D.svg?style=flat
[badge-platform-js-node]: https://img.shields.io/badge/-nodejs-68a063.svg?style=flat
[badge-platform-linux]: http://img.shields.io/badge/-linux-2D3F6C.svg?style=flat
[badge-platform-macos]: http://img.shields.io/badge/-macos-111111.svg?style=flat
[badge-platform-ios]: http://img.shields.io/badge/-ios-CDCDCD.svg?style=flat
[badge-platform-tvos]: http://img.shields.io/badge/-tvos-808080.svg?style=flat
[badge-platform-watchos]: http://img.shields.io/badge/-watchos-C0C0C0.svg?style=flat
[badge-platform-wasm]: https://img.shields.io/badge/-wasm-624FE8.svg?style=flat
[badge-platform-windows]: http://img.shields.io/badge/-windows-4D76CD.svg?style=flat
[badge-support-android-native]: http://img.shields.io/badge/support-[AndroidNative]-6EDB8D.svg?style=flat
[badge-support-apple-silicon]: http://img.shields.io/badge/support-[AppleSilicon]-43BBFF.svg?style=flat
[badge-support-js-ir]: https://img.shields.io/badge/support-[js--IR]-AAC4E0.svg?style=flat
[badge-support-linux-arm]: http://img.shields.io/badge/support-[LinuxArm]-2D3F6C.svg?style=flat
[badge-support-linux-mips]: http://img.shields.io/badge/support-[LinuxMIPS]-2D3F6C.svg?style=flat

[url-latest-release]: https://github.com/KotlinCrypto/MACs/releases/latest
[url-license]: https://www.apache.org/licenses/LICENSE-2.0.txt
[url-kotlin]: https://kotlinlang.org
[url-core]: https://github.com/KotlinCrypto/core
[url-hash]: https://github.com/KotlinCrypto/hash
[url-version-catalog]: https://github.com/KotlinCrypto/version-catalog
[url-docs]: https://macs.kotlincrypto.org
