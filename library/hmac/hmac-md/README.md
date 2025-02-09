# Module hmac-md

`Hmac` implementations for MD Hash-based Message Authentication Codes

Implementations for:
 - HmacMD5

See [HERE][url-mac-usage] for basic usage example of `Mac`.

```kotlin
// Using CryptoRand from KotlinCrypto/random repo as an example
import org.kotlincrypto.random.CryptoRand
// ...

fun main() {
    val key = CryptoRand.Default.nextBytes(ByteArray(100))

    HmacMD5(key)
}
```

[url-mac-usage]: https://core.kotlincrypto.org/library/mac/index.html
