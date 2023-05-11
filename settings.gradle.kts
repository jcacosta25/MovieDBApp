pluginManagement {
    repositories {
        maven("https://plugins.gradle.org/m2/")
        gradlePluginPortal()
        maven(url = "https://storage.googleapis.com/r8-releases/raw")
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        gradlePluginPortal()
        maven(url = "https://storage.googleapis.com/r8-releases/raw")
    }
}
rootProject.buildFileName = "build.gradle.kts"
rootProject.name = "MovieDBApp"
include("provider")
include("app")
