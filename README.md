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
![badge-platform-linux]
![badge-platform-macos]
![badge-platform-ios]
![badge-platform-tvos]
![badge-platform-watchos]
![badge-platform-wasm]
![badge-platform-windows]
![badge-support-android-native]
![badge-support-apple-silicon]
![badge-support-js-ir]
![badge-support-linux-arm]
![badge-support-linux-mips]

Message Authentication Code algorithms for Kotlin Multiplatform

If you are looking for hashing algorithms (e.g. `SHA-256`, `SHA3-256`, etc), see the [hash repo][url-hash].

If you are looking for `Encoding` (`Base16` a.k.a. `hex`, `Base32`, `Base64`, etc), see the [encoding repo][url-encoding].

If you are looking for `SecureRandom`, see the [secure-random repo][url-secure-random].

### Usage

```kotlin
fun main() {
    // NOTE: SecureRandom is not included as a dependency in
    //       MACs. It is only being used here as an example
    //       because one should never derive a key using Random.
    val key = SecureRandom().nextBytesOf(100)
    val random = Random.Default.nextBytes(615)

    HmacMD5(key).apply { update(random) }.doFinal()

    HmacSHA1(key).doFinal(random)
}
```

`SHA-2`
```kotlin
fun main() {
    val key = SecureRandom().nextBytesOf(100)
    val random = Random.Default.nextBytes(615)

    HmacSHA224(key).doFinal(random)

    HmacSHA256(key).apply { update(random) }.doFinal(random)

    HmacSha384(key).doFinal(random)

    val hMacSha512 = HmacSHA512(key)

    val hMacSha512Bytes = hMacSha512.apply {
        update(random[0])
        update(random[20])
        update(random, 25, 88)
    }.doFinal() // is automatically reset for re-use when doFinal() is called

    // All Mac's are copyable
    val hMacSha512Copy = hMacSha512.apply { update(random) }.copy()
    val copyBytes = hMacSha512Copy.doFinal()
    val resetBytes = hMacSha512.reset().doFinal()
    
    HmacSHA512_224(key).doFinal(random)
    HmacSHA512_256(key).doFinal(random)
}
```

`SHA-3`
```kotlin
 fun main() {
    val key = SecureRandom().nextBytesOf(100)

    HmacKeccak224(key)
    HmacKeccak256(key)
    HmacKeccak384(key)
    HmacKeccak512(key)
    HmacSHA3_224(key)
    HmacSHA3_256(key)
    HmacSHA3_384(key)
    HmacSHA3_512(key)
}
```

<!-- TODO: Uncomment
`KMAC`
```kotlin
fun main() {
    val key = SecureRandom().nextBytesOf(100)
    
    KMAC128(key)     // as a Mac
    KMAC128.xOf(key) // as a Xof (Extendable-Output Function)

    KMAC256(key)

    val xof = KMAC256.xOf(key)
    xof.update(Random.Default.nextBytes(500))
    val out1 = ByteArray(200)
    val out2 = ByteArray(12345)
    xof.use { read(out1); read(out2) }
}
```
-->

### Get Started

The best way to keep `KotlinCrypto` dependencies up to date is by using the 
[version-catalog][url-version-catalog]. Alternatively, you can use the BOM as 
shown below.

<!-- TAG_VERSION -->

<!-- TODO: Add to dependencies
    // KMAC128, KMAC256
    implementation("org.kotlincrypto.macs:kmac")
-->

```kotlin
// build.gradle.kts
dependencies {
    // define the BOM and its version
    implementation(platform("org.kotlincrypto.macs:bom:0.2.3"))

    // define artifacts without version
    
    // HmacMD5
    implementation("org.kotlincrypto.macs:hmac-md5")
    
    // HmacSHA1
    implementation("org.kotlincrypto.macs:hmac-sha1")
    
    // HmacSHA224, HmacSHA256, HmacSHA384, HmacSHA512, HmacSHA512/224, HmacSHA512/256
    implementation("org.kotlincrypto.macs:hmac-sha2")
    
    // HmacKeccak224, HmacKeccak256, HmacKeccak384, HmacKeccak512
    // HmacSHA3-224, HmacSHA3-256, HmacSHA3-384, HmacSHA3-512
    implementation("org.kotlincrypto.macs:hmac-sha3")
}
```

<!-- TAG_VERSION -->
[badge-latest-release]: https://img.shields.io/badge/latest--release-0.2.3-blue.svg?style=flat
[badge-license]: https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat

<!-- TAG_DEPENDENCIES -->
[badge-kotlin]: https://img.shields.io/badge/kotlin-1.8.10-blue.svg?logo=kotlin
[badge-core]: https://img.shields.io/badge/kotlincrypto.core-0.2.3-blue.svg
[badge-hash]: https://img.shields.io/badge/kotlincrypto.hash-0.2.3-blue.svg

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
[url-encoding]: https://github.com/05nelsonm/encoding
[url-version-catalog]: https://github.com/KotlinCrypto/version-catalog
[url-secure-random]: https://github.com/KotlinCrypto/secure-random
