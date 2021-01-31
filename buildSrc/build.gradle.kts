plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}
repositories {
    google()
    jcenter()
    maven { url = uri("https://plugins.gradle.org/m2/") }
}

dependencies {
    implementation("com.android.tools.build:gradle:4.1.2")
    implementation("org.jmailen.gradle:kotlinter-gradle:3.3.0")
}