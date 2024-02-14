import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

class ConfigScreen : Plugin<Project> by local plugin {
    tasks.withType<KotlinJvmCompile> {
        compilerOptions {
            freeCompilerArgs.add("-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi")
        }
    }
    dependencies {
        "implementation"(project(":library:architecture"))
        "implementation"(project(":library:localisation"))
        "implementation"(project(":library:mvvm"))
        "implementation"(project(":library:navigation"))
        "implementation"(project(":library:result"))

        "implementation"(project(":design:compose"))

        "implementation"(libs.dependency("activity.compose"))
        "implementation"(platform(libs.dependency("compose.bom")))
        "implementation"(libs.dependency("ui"))
        "implementation"(libs.dependency("ui.graphics"))
        "implementation"(libs.dependency("androidx.ui.tooling"))
        "implementation"(libs.dependency("material"))
        "implementation"(libs.dependency("material3"))
        "implementation"(libs.dependency("androidx-navigation-compose"))

        "implementation"(libs.dependency("koin.core"))
        "implementation"(libs.dependency("koin.android"))
        "implementation"(libs.dependency("koin.androidx.compose"))
        "implementation"(libs.dependency("junit.params"))

        "testImplementation"(libs.dependency("coroutines.test"))
        "testImplementation"(libs.dependency("kotest.assertions"))
        "testImplementation"(libs.dependency("junit.core"))
        "testImplementation"(libs.dependency("mockk"))
        "testImplementation"(libs.dependency("mockk.agent"))

        "testImplementation"(testFixtures(project(":library:architecture")))
        "testImplementation"(project(":library:mvvm-fixtures"))
        "testImplementation"(project(":library:localisation-fixtures"))
    }
}