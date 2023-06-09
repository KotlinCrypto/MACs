# CHANGELOG

## Version 0.2.7 (2023-06-09)
 - Updates `kotlincrypto.core` to `0.2.7` [[#36]][36]
     - Fix for Android API 23 and below where `javax.crypto.Mac.doFinal` does
       not call `javax.crypto.MacSpi.engineReset` [[#core 46]][core-46]
 - Updates `kotlincrypto.hash` to `0.2.7` [[#36]][36]

## Version 0.2.6 (2023-06-08)
 - Updates `kotlincrypto.core` to `0.2.6` [[#33]][33]
     - Fix for Android API 21-23 requiring a `Provider` for `javax.crypto.Mac`
       in order to set `spiImpl` [[core #44]][core-44]
 - Updates `kotlincrypto.hash` to `0.2.6`

## Version 0.2.5 (2023-06-07)
 - Updates `kotlincrypto.core` to `0.2.5` [[#31]][31]
     - Fix for Android API 23 and below not accepting `null` for `Mac.init` key
       parameter [[core #38]][core-38]
 - Updates `kotlincrypto.hash` to `0.2.5` [[#31]][31]
 - Updates `kotlin` to `1.8.21` [[#31]][31]
 - `Hmac` now **always** copies provided key bytes and blanks the copy
   after deriving `iKey` and `oKey` [[#32]][32]

## Version 0.2.4 (2023-04-16)
 - Updates `kotlincrypto.core` to `0.2.4`
 - Updates `kotlincrypto.hash` to `0.2.4`
 - Adds a new module `kmac` [[#26]][26]
     - Adds `KMAC128`
     - Adds `KMAC256`

## Version 0.2.3 (2023-04-08)
 - Adds a new module, `hmac-sha3` [[#21]][21]
     - Adds `HmacKeccak224`
     - Adds `HmacKeccak256`
     - Adds `HmacKeccak384`
     - Adds `HmacKeccak512`
     - Adds `HmacSHA3-224`
     - Adds `HmacSHA3-256`
     - Adds `HmacSHA3-384`
     - Adds `HmacSHA3-512`
 - Cleans up `Hmac` implementation [[#21]][21]
 - Updates `kotlincrypto.core` to `0.2.3` [[#23]][23]
 - Updates `kotlincrypto.hash` to `0.2.3` [[#23]][23]

## Version 0.2.1 (2023-03-28)
 - Updates `kotlincrypto.core` to `0.2.0`
 - Updates `kotlincrypto.hash` to `0.2.1`

## Version 0.2.0 (2023-03-12)
 - Adds `HmacSHA224`
 - Adds `HmacSHA384`
 - Adds `HmacSHA512t`
 - Combines all `hmac-sha2-*` algorithms into single `hmac-sha2` module

## Version 0.1.0 (2023-03-06)
 - Fixes `Hmac.Engine` implementation where `offset` or `len` 
   were not being passed to the `Digest`
 - Updates `kotlincrypto.core` to `0.1.1`
 - Updates `kotlincrypto.hash` to `0.1.2`
 - Removes `hmac` from the `BOM` dependency

## Version 0.1.0 (2023-03-05)
 - Initial Release

[core-38]: https://github.com/KotlinCrypto/core/pull/38
[core-44]: https://github.com/KotlinCrypto/core/pull/44
[core-46]: https://github.com/KotlinCrypto/core/pull/46
[21]: https://github.com/KotlinCrypto/MACs/pull/21
[23]: https://github.com/KotlinCrypto/MACs/pull/23
[26]: https://github.com/KotlinCrypto/MACs/pull/26
[31]: https://github.com/KotlinCrypto/MACs/pull/31
[32]: https://github.com/KotlinCrypto/MACs/pull/32
[33]: https://github.com/KotlinCrypto/MACs/pull/33
[36]: https://github.com/KotlinCrypto/MACs/pull/36
