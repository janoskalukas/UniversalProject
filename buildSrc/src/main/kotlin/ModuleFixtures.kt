import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

/**
 * Gradle configuration module for 'test fixtures' module of all module types.
 */
class ModuleFixtures : Plugin<Project> by local plugin {
    apply<ConfigAndroidBuild>()
    apply<ConfigCompiler>()

    kotlin {
        explicitApi()
        explicitApiAndroid()
    }

    dependencies {
        "implementation"(libs.dependency("activity.compose"))
        "implementation"(libs.dependency("androidx.lifecycle.viewmodel.ktx"))
    }
}