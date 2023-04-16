# CHANGELOG

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

[21]: https://github.com/KotlinCrypto/MACs/pull/21
[23]: https://github.com/KotlinCrypto/MACs/pull/23
[26]: https://github.com/KotlinCrypto/MACs/pull/26
