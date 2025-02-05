# Module hmac-sha1

`Hmac` implementations for SHA1 Hash-based Message Authentication Codes

Implementations for:
 - HmacSHA1

See [HERE][url-mac-usage] for basic usage example of `Mac`.

```kotlin
// Using SecureRandom from the secure-random repo as an example
import org.kotlincrypto.SecureRandom
// ...

fun main() {
    val key = SecureRandom().nextBytesOf(100)

    HmacSHA1(key)
}
```

[url-mac-usage]: https://core.kotlincrypto.org/library/mac/index.html
