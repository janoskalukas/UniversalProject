plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.universal.library.localisation.fixtures"
}

apply<ModuleFixtures>()

dependencies {
    api(project(":library:localisation"))
    implementation(libs.mockk)
}