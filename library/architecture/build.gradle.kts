import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    kotlin("jvm")
    `java-test-fixtures`
}

tasks.withType<KotlinJvmCompile> {
    compilerOptions {
        freeCompilerArgs.add("-opt-in=kotlinx.coroutines.FlowPreview")
    }
}

dependencies {
    api(libs.coroutines.core)
    testFixturesApi(libs.junit.core)
    testFixturesApi(libs.coroutines.test)
    testFixturesImplementation(libs.mockk)
}