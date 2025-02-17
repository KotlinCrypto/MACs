rootProject.name = "MACs"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        google()
    }
}

includeBuild("build-logic")

@Suppress("PrivatePropertyName")
private val CHECK_PUBLICATION: String? by settings

if (CHECK_PUBLICATION != null) {
    include(":tools:check-publication")
} else {
    listOf(
        "blake2",
        "hmac:hmac",
        "hmac:hmac-md",
        "hmac:hmac-sha1",
        "hmac:hmac-sha2",
        "hmac:hmac-sha3",
        "kmac",
    ).forEach { name ->
        include(":library:$name")
    }

//    include(":benchmarks")
    include(":bom")
    include(":tools:testing")
    include(":test-android")
}
