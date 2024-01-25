plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.universal.library.navigation"
}

apply<ConfigCompiler>()
apply<ConfigAndroidBuild>()

dependencies {
    implementation(libs.activity.compose)
    implementation(libs.bundles.navigation)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
}