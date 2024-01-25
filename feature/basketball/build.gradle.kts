plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.universal.feature.basketball"
}

apply<ConfigAndroidBuild>()
apply<ConfigCompiler>()
apply<ConfigScreen>()

dependencies {
    implementation(project(":networking"))

    implementation(libs.compose.paging.runtime)
    implementation(libs.compose.paging)
}