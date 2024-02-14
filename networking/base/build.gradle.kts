plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.universal.networking.base"
}

apply<ConfigNetworking>()