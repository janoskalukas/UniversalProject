pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Universal"
include(":app")

include(":feature:basketball")

include(":networking:bff.base")

include(":library:architecture")
include(":library:mvvm")
include(":library:navigation")
include(":library:result")

include(":design:compose")

 