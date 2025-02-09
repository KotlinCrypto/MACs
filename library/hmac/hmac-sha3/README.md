# Module hmac-sha3

`Hmac` implementations for SHA3 Hash-based Message Authentication Codes

Implementations for:
 - HmacKeccak224
 - HmacKeccak256
 - HmacKeccak384
 - HmacKeccak512
 - HmacSHA3-224
 - HmacSHA3-256
 - HmacSHA3-384
 - HmacSHA3-512

See [HERE][url-mac-usage] for basic usage example of `Mac`.

```kotlin
// Using CryptoRand from KotlinCrypto/random repo as an example
import org.kotlincrypto.random.CryptoRand
// ...

fun main() {
    val key = CryptoRand.Default.nextBytes(ByteArray(100))

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

[url-mac-usage]: https://core.kotlincrypto.org/library/mac/index.html
