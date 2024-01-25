import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.the

@Suppress("ClassName")
internal object local

internal infix fun local.plugin(config: Project.() -> Unit) = Plugin<Project> { config(it) }

internal val Project.libs: VersionCatalog
    get() = the<VersionCatalogsExtension>().named("libs")

internal fun VersionCatalog.dependency(alias: String): MinimalExternalModuleDependency {
    return findLibrary(alias).get().get()
}