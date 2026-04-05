pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "ParcheApp"
include(":app")
include(":core:core-ui")
include(":core:core-common")
include(":feature:feature-home")
include(":feature:feature-partido")
include(":feature:feature-event-detail")
include(":feature:feature-chat")
include(":feature:feature-profile")
