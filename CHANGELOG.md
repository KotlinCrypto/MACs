# CHANGELOG

## Version 0.2.0 (2023-03-12)
- Adds `HmacSHA224` algorithm
- Adds `HmacSHA384` algorithm
- Adds `HmacSHA512t` algorithm
- Combines all `hmac-sha2-*` algorithms into single `sha2` module

## Version 0.1.0 (2023-03-06)
 - Fixes `Hmac.Engine` implementation where `offset` or `len` 
   were not being passed to the `Digest`
 - Updates `kotlincrypto.core` to `0.1.1`
 - Updates `kotlincrypto.hash` to `0.1.2`
 - Removes `hmac` from the `BOM` dependency

## Version 0.1.0 (2023-03-05)
 - Initial Release
