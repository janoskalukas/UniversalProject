plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.universal.library.localisation"
}

apply<ConfigCompiler>()
apply<ConfigAndroidBuild>()

dependencies {
    implementation(libs.activity.compose)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
}