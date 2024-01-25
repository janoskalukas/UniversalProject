import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class ConfigScreen : Plugin<Project> by local plugin {
    dependencies {
        "implementation"(project(":library:architecture"))
        "implementation"(project(":library:mvvm"))
        "implementation"(project(":library:navigation"))
        "implementation"(project(":library:result"))

        "implementation"(project(":design:compose"))

        "implementation"(libs.dependency("activity.compose"))
        "implementation"(platform(libs.dependency("compose.bom")))
        "implementation"(libs.dependency("ui"))
        "implementation"(libs.dependency("ui.graphics"))
        "implementation"(libs.dependency("ui.tooling.preview"))
        "implementation"(libs.dependency("material"))
        "implementation"(libs.dependency("material3"))
        "implementation"(libs.dependency("androidx-navigation-compose"))

        "implementation"(libs.dependency("koin.core"))
        "implementation"(libs.dependency("koin.android"))
        "implementation"(libs.dependency("koin.androidx.compose"))
    }
}