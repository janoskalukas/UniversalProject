plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.universal.library.paging"
}

apply<ConfigCompiler>()
apply<ConfigAndroidBuild>()

dependencies {
    implementation(libs.compose.paging.runtime)
    implementation(libs.compose.paging)
}