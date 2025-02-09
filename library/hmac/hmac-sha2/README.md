# Module hmac-sha2

`Hmac` implementations for SHA2 Hash-based Message Authentication Codes

Implementations for:
 - HmacSHA224
 - HmacSHA256
 - HmacSHA384
 - HmacSHA512
 - HmacSHA512/t

See [HERE][url-mac-usage] for basic usage example of `Mac`.

```kotlin
// Using CryptoRand from KotlinCrypto/random repo as an example
import org.kotlincrypto.random.CryptoRand
// ...

fun main() {
    val key = CryptoRand.Default.nextBytes(ByteArray(100))

    HmacSHA224(key)
    HmacSHA256(key)
    HmacSha384(key)
    HmacSHA512(key)

    HmacSHA512t(key, t = 224) // HmacSHA512/224
    HmacSHA512t(key, t = 256) // HmacSHA512/256
}
```

[url-mac-usage]: https://core.kotlincrypto.org/library/mac/index.html
