

plugins {
    id("org.jmailen.kotlinter")
    jacoco
}

apply {
    from("reports.gradle.kts")
}
buildscript {
    repositories {
        google()
        jcenter()
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

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://plugins.gradle.org/m2/")
        mavenCentral()
    }
}
