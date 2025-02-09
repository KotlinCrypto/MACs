# Module blake2

`Mac` implementations for BLAKE2 Keyed Hashing

Implementations for:
 - BLAKE2b
 - BLAKE2s

See [HERE][url-mac-usage] for basic usage example of `Mac`.

```kotlin
// Using CryptoRand from KotlinCrypto/random repo as an example
import org.kotlincrypto.random.CryptoRand
// ...

fun main() {
    val key64 = CryptoRand.Default.nextBytes(ByteArray(64))
    val key32 = CryptoRand.Default.nextBytes(ByteArray(32))

    BLAKE2b(key64, 512)
    BLAKE2s(key32, 256)
}
```

[url-mac-usage]: https://core.kotlincrypto.org/library/mac/index.html
