//import org.gradle.api.JavaVersion
//import org.gradle.api.Plugin
//import org.gradle.api.Project
//import org.gradle.jvm.toolchain.JavaLanguageVersion
//import org.gradle.kotlin.dsl.dependencies
//import org.gradle.kotlin.dsl.withType
//import org.jetbrains.kotlin.gradle.dsl.JvmTarget
//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
//import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
//
//class ConfigCompiler : Plugin<Project> by local plugin {
//
//    val targetJvmVersion = JavaVersion.VERSION_17
//
//    java {
//        sourceCompatibility = targetJvmVersion
//        targetCompatibility = targetJvmVersion
//    }
//
//    android {
//        compileOptions {
//            sourceCompatibility = targetJvmVersion
//            targetCompatibility = targetJvmVersion
//            isCoreLibraryDesugaringEnabled = true
//        }
//    }
//
//    androidApplication {
//        compileOptions {
//            sourceCompatibility = targetJvmVersion
//            targetCompatibility = targetJvmVersion
//            isCoreLibraryDesugaringEnabled = true
//        }
//    }
//
//    kotlin {
//        jvmToolchain {
//            languageVersion.set(JavaLanguageVersion.of(targetJvmVersion.majorVersion))
//        }
//    }
//
//    tasks.withType<KotlinJvmCompile> {
//        compilerOptions {
//            jvmTarget.set(JvmTarget.fromTarget(targetJvmVersion.majorVersion))
//            freeCompilerArgs.add("-opt-in=kotlin.time.ExperimentalTime")
//        }
//    }
//
//    tasks.withType<KotlinCompile> {
//        kotlinOptions.allWarningsAsErrors = true
//    }
//
//    dependencies {
//        if (configurations.any { it.name == "coreLibraryDesugaring" }) {
//            "coreLibraryDesugaring"(libs.dependency("android.desugar"))
//        }
//    }
//}