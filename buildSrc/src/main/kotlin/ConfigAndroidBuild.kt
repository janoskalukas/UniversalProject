import org.gradle.api.Plugin
import org.gradle.api.Project

class ConfigAndroidBuild : Plugin<Project> by local plugin {
    android {
        compileSdk = Config.compileSdk

        defaultConfig {
            minSdk = Config.minSdk
        }
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.8"
        }
    }

    androidApplication {
        compileSdk = Config.compileSdk

        defaultConfig {
            minSdk = Config.minSdk
            targetSdk = Config.targetSdk
        }
    }
}