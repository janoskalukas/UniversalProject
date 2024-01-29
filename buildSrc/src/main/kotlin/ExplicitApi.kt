import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

fun Project.explicitApiAndroid() {
    tasks.withType<KotlinJvmCompile> {
        compilerOptions {
            freeCompilerArgs.add("-Xexplicit-api=strict")
        }
    }
}