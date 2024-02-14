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
    implementation(project(":networking:nba"))
    implementation(project(":library:paging"))

    implementation(libs.compose.paging.runtime)
    implementation(libs.compose.paging)
    implementation(libs.glide)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.compose.ui.preview)
}