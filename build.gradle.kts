import io.gitlab.arturbosch.detekt.Detekt

//// Top-level build file where you can add configuration options common to all sub-projects/modules.
////region Dependency Version Updates
//

plugins {
    alias(libs.plugins.detekt)
}

fun isStable(version: String): Boolean {
    val keyword = setOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    return keyword || regex.matches(version)
}
//endregion

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}

//region Detekt
dependencies {
    detektPlugins(libs.detekt.ktLint)
}

detekt {
    source.setFrom(files("$projectDir"))
    config.setFrom(files("$projectDir/detekt.yml"))
    baseline = null
    parallel = true
}

tasks.withType<Detekt> {
    exclude("**/build/**")
    exclude("**/buildSrc/**")
    exclude("**/*gradle.kts")
}