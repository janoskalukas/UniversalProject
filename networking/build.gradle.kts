plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.universal.networking"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

apply<ConfigCompiler>()
apply<ConfigAndroidBuild>()

dependencies {
    implementation(libs.retrofit)
    implementation(libs.moshi)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logginginterceptor)
    implementation(libs.koin.core)
    implementation(libs.activity.compose)
}