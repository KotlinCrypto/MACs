# CHANGELOG

## Version 0.7.1 (2025-08-25)
 - Updates `kotlin` to `2.2.10` [[#78]][78]
 - Updates `kotlincrypto.core` to `0.7.1`
 - Updates `kotlincrypto.hash` to `0.7.1`

## Version 0.7.0 (2025-02-25)
 - Updates `kotlin` to `2.1.10` [[#74]][74]
 - Updates `kotlincrypto.core` to `0.7.0` [[#74]][74]
 - Updates `kotlincrypto.hash` to `0.7.0` [[#74]][74]
 - Replaces `BLAKE2Mac.DigestFactory` functional interface with `KFunction` constructor 
   invocation for `BLAKE2Digest` creation [[#76]][76]
 - All implementations now throw `InvalidKeyException` instead of `IllegalArgumentException` on 
   instantiation when `key` parameter is inappropriate [[#74]][74]
     - **NOTE:** The `Mac` abstraction for `reset(newKey)` now also throws `InvalidKeyException` 
       instead of `IllegalArgumentException`. See [[core PR #124]][124-core].
 - The following now throw `InvalidParameterException` instead of `IllegalArgumentException` on 
   instantiation when a parameter is inappropriate [[#74]][74]:
     - `blake2/BLAKE2b`
     - `blake2/BLAKE2s`
     - `hmac/Hmac`
     - `hmac-sha2/HmacSHA512t`
     - `kmac/KMAC128`
     - `kmac/KMAC256`

## Version 0.6.1 (2025-02-09)
 - Updates `kotlincrypto.core` to `0.6.1`
 - Updates `kotlincrypto.hash` to `0.6.1`
 - Implements new `Mac.Engine.doFinalInto` API for all implementations [[#71]][71]
 - Fixes unnecessary calls to `Digest.copy` & `Digest.reset` when `Hmac.Engine` is instantiated [[#70]][70]
 - Fixes double resets on `doFinal` of underlying `Digest` by setting `Mac.Engine.resetOnDoFinal` to 
   `false` for all implementations [[#72]][72]
 - Adds `dokka` documentation at `https://MACs.kotlincrypto.org` [[#73]][73]

## Version 0.6.0 (2025-01-15)
 - Adds `BLAKE2b` and `BLAKE2s` message authentication.
 - Updates `KotlinCrypto.core` and `KotlinCrypto.hash` to `0.6.0`
 - Updates all implementations to conform to `KotlinCrypto.core` `0.6.0` modifications 
   to `Mac` and `Mac.Engine` internal API.
 - All implementations now define a proper return type for `copy` (instead of `Mac`).
 - All implementations can now be reinitialized with a new `key` via `Mac.reset(newKey)`
 - Removes all `@Throws` annotations from constructors (it is documented).
 - Finalizes `Hmac` API and removes `InternalKotlinCryptoApi` opt-in requirement from constructors.
 - Deprecates `HmacSHA512_224` and `HmacSHA512_256` top-level functions.
 - `Kmac` now implements `ReKeyableXofAlgorithm` so that the `Xof` can be reinitialized via `Xof.Companion.reset`.
 - `Xof<Kmac>` instances now zero out the key block for the copy of the underlying `Mac.Engine` whenever 
   a new `Xof.Reader` is created.

## Version 0.5.6 (2024-12-28)
 - Updates `kotlincrypto.hash` to `0.5.6` [[#56]][56]

## Version 0.5.5 (2024-12-20)
 - Updates `kotlincrypto.core` to `0.5.5`
 - Updates `kotlincrypto.hash` to `0.5.5`
 - Fixes `hmac-sha3` and `kmac` performance issues for Jvm [[#55]][55]

## Version 0.5.4 (2024-12-19)
 - Updates `kotlincrypto.core` to `0.5.4`
 - Updates `kotlincrypto.hash` to `0.5.4`
 - Adds benchmarking to repository [[#53]][53]
     - Benchmark comparisons using `core` and `sponges` performance
       improvements can be viewed in [comment PR #54][54-comment]
     - TL;DR huge gains for `hmac-sha3` & `kmac`

## Version 0.5.3 (2024-08-31)
 - Updates `kotlincrypto.core` to `0.5.3`
 - Updates `kotlincrypto.hash` to `0.5.3`
 - Updates `kotlin` to `1.9.24` [[#50]][50]
 - Fixes multiplatform metadata manifest `unique_name` parameter for
   all source sets to be truly unique. [[#50]][50]
 - Updates jvm `.kotlin_module` with truly unique file name. [[#50]][50]

## Version 0.5.1 (2024-03-19)
 - Updates `kotlincrypto.core` to `0.5.1` [[#46]][46]
 - Updates `kotlincrypto.hash` to `0.5.1` [[#46]][46]
 - Updates `kotlin` to `1.9.23` [[#46]][46]
 - Add experimental support for `wasmJs` & `wasmWasi` [[#46]][46]
 - Add support for Java9 `JPMS` via Multi-Release jar [[#47]][47]

## Version 0.4.0 (2023-11-30)
- Updates `kotlincrypto.core` to `0.4.0` [[#42]][42]
- Updates `kotlincrypto.hash` to `0.4.0` [[#42]][42]
- Updates `kotlin` to `1.9.21` [[#42]][42]
- Drops support for the following deprecated targets:
    - `iosArm32`
    - `watchosX86`
    - `linuxArm32Hfp`
    - `linuxMips32`
    - `linuxMipsel32`
    - `mingwX86`
    - `wasm32`

## Version 0.3.0 (2023-06-28)
 - Fixes JPMS split packages [[#40]][40]
     - **API BREAKING CHANGES**
     - Package names were changed for `hmac`, `hmac-md5`, `hmac-sha1`, `hmac-sha2`, `hmac-sha3`, 
       and `kmac` modules
     - Example:
         - `org.kotlincrypto.macs.Hmac` was moved to `org.kotlincrypto.macs.hmac.Hmac`
         - `org.kotlincrypto.macs.HmacSHA256` was moved to `org.kotlincrypto.macs.hmac.sha2.HmacSHA256`
         - `org.kotlincrypto.macs.HmacSHA3_256` was moved to `org.kotlincrypto.macs.hmac.sha3.HmacSHA3_256`
         - `org.kotlincrypto.macs.KMAC128` was moved to `org.kotlincrypto.macs.kmac.KMAC128`
 - The MavenCentral dependency `org.kotlincrypto.macs:hmac-md5` is now deprecated,
   in favor of `org.kotlincrypto.macs:hmac-md`
     - `hmac-md5` dependency now simply provides the `hmac-md` dependency and
       will continue to be published until the next major version release.
 - The following MavenCentral dependencies (previously deprecated) have
   been removed from publication [[#41]][41]
     - `org.kotlincrypto.macs:hmac-sha2-256`
     - `org.kotlincrypto.macs:hmac-sha2-512`
 - `Kmac` performance improvements [[#38]][38]
 - See the [ANNOUNCEMENT #3][3-discussion] for more information on `0.3.0` release

## Version 0.2.7 (2023-06-09)
 - Updates `kotlincrypto.core` to `0.2.7` [[#36]][36]
     - Fix for Android API 23 and below where `javax.crypto.Mac.doFinal` does
       not call `javax.crypto.MacSpi.engineReset` [[core PR #46]][46-core]
 - Updates `kotlincrypto.hash` to `0.2.7` [[#36]][36]

## Version 0.2.6 (2023-06-08)
 - Updates `kotlincrypto.core` to `0.2.6` [[#33]][33]
     - Fix for Android API 21-23 requiring a `Provider` for `javax.crypto.Mac`
       in order to set `spiImpl` [[core PR #44]][44-core]
 - Updates `kotlincrypto.hash` to `0.2.6`

## Version 0.2.5 (2023-06-07)
 - Updates `kotlincrypto.core` to `0.2.5` [[#31]][31]
     - Fix for Android API 23 and below not accepting `null` for `Mac.init` key
       parameter [[core PR #38]][38-core]
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

[3-discussion]: https://github.com/orgs/KotlinCrypto/discussions/3
[21]: https://github.com/KotlinCrypto/MACs/pull/21
[23]: https://github.com/KotlinCrypto/MACs/pull/23
[26]: https://github.com/KotlinCrypto/MACs/pull/26
[31]: https://github.com/KotlinCrypto/MACs/pull/31
[32]: https://github.com/KotlinCrypto/MACs/pull/32
[33]: https://github.com/KotlinCrypto/MACs/pull/33
[36]: https://github.com/KotlinCrypto/MACs/pull/36
[38-core]: https://github.com/KotlinCrypto/core/pull/38
[38]: https://github.com/KotlinCrypto/MACs/pull/38
[40]: https://github.com/KotlinCrypto/MACs/pull/40
[41]: https://github.com/KotlinCrypto/MACs/pull/41
[42]: https://github.com/KotlinCrypto/MACs/pull/42
[44-core]: https://github.com/KotlinCrypto/core/pull/44
[46-core]: https://github.com/KotlinCrypto/core/pull/46
[46]: https://github.com/KotlinCrypto/MACs/pull/46
[47]: https://github.com/KotlinCrypto/MACs/pull/47
[50]: https://github.com/KotlinCrypto/MACs/pull/50
[53]: https://github.com/KotlinCrypto/MACs/pull/53
[54-comment]: https://github.com/KotlinCrypto/MACs/pull/54#issuecomment-2554991551
[55]: https://github.com/KotlinCrypto/MACs/pull/55
[56]: https://github.com/KotlinCrypto/MACs/pull/56
[70]: https://github.com/KotlinCrypto/MACs/pull/70
[71]: https://github.com/KotlinCrypto/MACs/pull/71
[72]: https://github.com/KotlinCrypto/MACs/pull/72
[73]: https://github.com/KotlinCrypto/MACs/pull/73
[74]: https://github.com/KotlinCrypto/MACs/pull/74
[76]: https://github.com/KotlinCrypto/MACs/pull/76
[78]: https://github.com/KotlinCrypto/MACs/pull/78
[124-core]: https://github.com/KotlinCrypto/core/pull/124
