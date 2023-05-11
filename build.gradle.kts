plugins {
    id("org.jmailen.kotlinter") version "3.12.0" apply false
    jacoco
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("org.jetbrains.kotlin.android") version "1.8.21" apply false
}
buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.0.1")
        classpath("org.jetbrains.kotlin:kotlin-android-extensions:1.8.21")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
        classpath("org.jacoco:org.jacoco.core:0.8.9")
        classpath("com.squareup:javapoet:1.13.0")
    }
}

apply {
    from("reports.gradle.kts")
}
