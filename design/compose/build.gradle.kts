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
    implementation(project(":library:localisation"))

    implementation(libs.material3.compose)
    implementation(libs.material)
    implementation(libs.ui)
    implementation(libs.glide)
    implementation(libs.compose.lottie)
}