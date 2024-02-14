plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.universal.networking.nba"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

apply<ConfigNetworking>()