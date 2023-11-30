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

### Usage

See [HERE][url-core-usage] for basic usage example for `Mac`.

```kotlin
// Using SecureRandom from the secure-random repo as an example
import org.kotlincrypto.SecureRandom
// ...

fun main() {
    val key = SecureRandom().nextBytesOf(100)
    
    // Hmacs that may be needed for backward compatibility but
    // should no longer be utilized because they have been broken.
    HmacMD5(key)
    HmacSHA1(key)
    
    key.fill(0)
}
```

`SHA2 Hmac`

```kotlin
fun main() {
    val key = SecureRandom().nextBytesOf(100)

    HmacSHA224(key)
    HmacSHA256(key)
    HmacSha384(key)
    HmacSHA512(key)

    HmacSHA512_224(key)
    HmacSHA512_256(key)
    HmacSHA512t(key, 504)
    
    key.fill(0)
}
```

`SHA3 Hmac`

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
    
    key.fill(0)
}
```

`SHA3 KMAC & XOFs` (i.e. [Extendable-Output Functions][url-pub-xof])

See [HERE][url-core-usage] for details on what `XOFs` are, and a basic usage example for `Xof`.

```kotlin
fun main() {
    val key = SecureRandom().nextBytesOf(100)

    val S = "My Customization".encodeToByteArray()

    // Macs
    KMAC128(key)
    KMAC256(key, S, outputLength = 123) // returns 123 bytes instead of the default when doFinal() is invoked
    
    // Xofs
    KMAC128.xOf(key, S)
    KMAC256.xOf(key)

    key.fill(0)
}
```

### Get Started

The best way to keep `KotlinCrypto` dependencies up to date is by using the 
[version-catalog][url-version-catalog]. Alternatively, you can use the BOM as 
shown below.

<!-- TAG_VERSION -->

```kotlin
// build.gradle.kts
dependencies {
    // define the BOM and its version
    implementation(platform("org.kotlincrypto.macs:bom:0.4.0"))

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
}
```

<!-- TAG_VERSION -->
[badge-latest-release]: https://img.shields.io/badge/latest--release-0.4.0-blue.svg?style=flat
[badge-license]: https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat

<!-- TAG_DEPENDENCIES -->
[badge-kotlin]: https://img.shields.io/badge/kotlin-1.9.21-blue.svg?logo=kotlin
[badge-core]: https://img.shields.io/badge/kotlincrypto.core-0.4.0-blue.svg
[badge-hash]: https://img.shields.io/badge/kotlincrypto.hash-0.4.0-blue.svg

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
[url-core-usage]: https://github.com/KotlinCrypto/core#usage
[url-hash]: https://github.com/KotlinCrypto/hash
[url-pub-xof]: https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.202.pdf
[url-version-catalog]: https://github.com/KotlinCrypto/version-catalog
