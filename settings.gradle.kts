rootProject.name = "MACs"

@Suppress("PrivatePropertyName")
private val CHECK_PUBLICATION: String? by settings

if (CHECK_PUBLICATION != null) {
    include(":tools:check-publication")
} else {
    listOf(
        "hmac:hmac",
        "hmac:hmac-md5",
        "hmac:hmac-sha1",
        "hmac:hmac-sha2-256",
        "hmac:hmac-sha2-512",
    ).forEach { name ->
        include(":library:$name")
    }

    include(":bom")
    include(":tools:testing")
}
