import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class ConfigNetworking : Plugin<Project> by local plugin {
    apply<ConfigCompiler>()
    apply<ConfigAndroidBuild>()
    dependencies {
        "implementation"(libs.dependency("retrofit"))
        "implementation"(libs.dependency("moshi"))
        "implementation"(libs.dependency("okhttp"))
        "implementation"(libs.dependency("okhttp.logginginterceptor"))
        "implementation"(libs.dependency("koin.core"))
        "implementation"(libs.dependency("activity.compose"))
    }
}