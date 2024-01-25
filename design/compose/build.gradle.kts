plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.universal.design.compose"
}

apply<ConfigCompiler>()
apply<ConfigAndroidBuild>()

dependencies {
    implementation(libs.material3.compose)
    implementation(libs.glide)
    implementation(libs.compose.lottie)
}