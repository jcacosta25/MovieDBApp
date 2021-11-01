plugins {
    ktlint
    jacoco
}
buildscript {
    repositories {
        google()
        maven("https://plugins.gradle.org/m2/")
        mavenCentral()
    }

    dependencies {
        classpath(BuildPlugins.android)
        classpath(BuildPlugins.kotlin)
        classpath(BuildPlugins.kotlinExt)
        classpath(BuildPlugins.navigation)
        classpath(BuildPlugins.ktlint)
        classpath(BuildPlugins.jacoco)
    }
}

apply {
    from("reports.gradle.kts")
}

allprojects {
    repositories {
        google()
        maven("https://plugins.gradle.org/m2/")
        mavenCentral()
    }
}

