import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.jvm.toolchain.JavaLanguageVersion

class ConfigCompiler : Plugin<Project> by local plugin {

    val targetJvmVersion = JavaVersion.VERSION_17

    java {
        sourceCompatibility = targetJvmVersion
        targetCompatibility = targetJvmVersion
    }

    android {
        compileOptions {
            sourceCompatibility = targetJvmVersion
            targetCompatibility = targetJvmVersion
        }
    }

    androidApplication {
        compileOptions {
            sourceCompatibility = targetJvmVersion
            targetCompatibility = targetJvmVersion
        }
    }

    kotlin {
        jvmToolchain {
            languageVersion.set(JavaLanguageVersion.of(targetJvmVersion.majorVersion))
        }
    }
}