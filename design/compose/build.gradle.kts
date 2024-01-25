plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.universal.feature.basketball"
    compileSdk = 33

    defaultConfig {
        minSdk = 28
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
}

dependencies {
    implementation(libs.material3.compose)
    implementation("com.github.bumptech.glide:compose:1.0.0-beta01")
}