object AndroidConfig {
    const val jvmTarget = "1.8"
    const val minSdk = 24
    const val compileSdk = 34

    val compileOptions = mapOf(
        "sourceCompatibility" to JavaVersion.VERSION_1_8,
        "targetCompatibility" to JavaVersion.VERSION_1_8
    )
}
