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

rootProject.name = "universal"
include(":app")

include(":feature:basketball")

include(":networking:nba")
include(":networking:base")

include(":library:architecture")
include(":library:localisation")
include(":library:localisation-fixtures")
include(":library:mvvm")
include(":library:mvvm-fixtures")
include(":library:navigation")
include(":library:result")
include(":library:paging")

include(":design:compose")

 