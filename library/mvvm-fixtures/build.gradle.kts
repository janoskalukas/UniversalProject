plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.universal.library.mvvm.fixtures"
}

apply<ModuleFixtures>()

dependencies {
    api(project(":library:mvvm"))
    implementation(testFixtures(project(":library:architecture")))
    api(libs.coroutines.test)
    implementation(libs.kotest.assertions)
}