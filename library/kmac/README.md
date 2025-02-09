# Module kmac

`Kmac` implementations for Keccak-based Message Authentication Codes

Implementations for:
 - KMAC128
 - KMAC256

See [HERE][url-mac-usage] for basic usage example of `Mac`.

```kotlin
// Using CryptoRand from KotlinCrypto/random repo as an example
import org.kotlincrypto.random.CryptoRand
// ...

fun main() {
    val key = CryptoRand.Default.nextBytes(ByteArray(100))

    // NIST.SP.800-185 derived functions
    val S = "My Customization".encodeToByteArray()

    // Macs
    KMAC128(key)
    // Return 123 bytes instead of the default
    // whenever doFinal() is called.
    KMAC256(key, S, outputLength = 123)
}
```

See [HERE][url-xof-usage] for basic usage example of `Xof` (i.e. [Extendable-Output Functions][url-pub-xof]).

```kotlin
fun main() {
    // NIST.SP.800-185 derived functions
    val S = "My Customization".encodeToByteArray()

    val xof: Xof<KMAC128> = KMAC128.xOf(key, S)
    KMAC256.xOf(key)
}
```

[url-mac-usage]: https://core.kotlincrypto.org/library/mac/index.html
[url-xof-usage]: https://core.kotlincrypto.org/library/xof/index.html
[url-pub-xof]: https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.202.pdf
