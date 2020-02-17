import org.jmailen.gradle.kotlinter.tasks.LintTask
import org.jmailen.gradle.kotlinter.tasks.FormatTask

plugins {
    application
    jacoco
}
buildscript {
    repositories {
        google()
        jcenter()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        mavenCentral()
    }

    dependencies {
        classpath(BuildPlugins.android)
        classpath(BuildPlugins.kotlin)
        classpath(BuildPlugins.kotlinExt)
        classpath(BuildPlugins.navigation)
        classpath(BuildPlugins.klint)
        classpath(BuildPlugins.jacoco)
    }
}
allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin(Libs.klint)
        plugin(Libs.jacoco)
    }
    jacoco {
        toolVersion = Versions.jacoco
        reportsDir = file("$buildDir/reports/jacoco")
    }

    val jacocoTestReport   by tasks.registering(JacocoReport::class) {
        dependsOn("testDebugUnitTest")
        reports {
            xml.isEnabled = false
            csv.isEnabled = false
            html.destination = file("$buildDir/reports/${project.name}/")
        }

        val excludes = listOf(
            "**/*\$run$1.class",
            "**/R.class",
            "**/R$*.class",
            "**/*\$ViewInjector*.*",
            "**/BuildConfig.*",
            "**/Manifest*.*",
            "**/*Test*.*",
            "android/**/*.*",
            "**/*Fragment.*",
            "**/*Activity.*",
            "**/*_MembersInjector.class",
            "**/Dagger*Component*.class",
            "**/Dagger*Subcomponent*.class",
            "**/*Subcomponent\$Builder.class",
            "**/*Module_*Factory.class",
            "**/*_MembersInjector*.*",
            "**/*_*Factory*.*",
            "**/androidTest/**",
            "**/*Component*.*",
            "**/*Module*.*",
            "$buildDir/generated/source/kapt/**"
        )
        classDirectories.setFrom(
            fileTree("dir" to "$buildDir/tmp/kotlin-classes/debug", "excludes" to excludes)
                .plus(fileTree("dir" to "$buildDir/intermediates/classes/debug", "excludes" to excludes))
                .plus(fileTree("build/classes/kotlin/main"))
                .plus(fileTree("dir" to "$buildDir/intermediates/classes/debug/com", "excludes" to excludes))
                .plus(fileTree("dir" to "$buildDir/tmp/kotlin-classes/debug/com", "excludes" to excludes))
        )
        executionData.setFrom(fileTree("dir" to project.buildDir, "include" to listOf("**/*.exec", "**/*.ec")))

        sourceDirectories.setFrom("${project.projectDir}/src/main/java")

    }

    val jacocoTestCoverageVerification by tasks.registering(JacocoCoverageVerification::class) {
        violationRules {
            rule {
                limit {
                    minimum = "0.5".toBigDecimal()
                }
            }

            rule {
                enabled = false
                element = "CLASS"
                includes = listOf("org.gradle.*")

                limit {
                    counter = "LINE"
                    value = "TOTALCOUNT"
                    maximum = "0.3".toBigDecimal()
                }
            }
        }
    }

    val ktLint by tasks.creating(LintTask::class) {
        group = "verification"
        source(files("src"))
        reports = mapOf(
            "plain" to file("$buildDir/reports/${project.name}/lint-report.txt"),
            "json" to file("$buildDir/reports/${project.name}/lint-report.json"),
            "html" to file("$buildDir/reports/${project.name}/lint-report.html")
        )
    }

    val ktFormat by tasks.creating(FormatTask::class) {
        group = "formatting"
        source(files("src"))
        report = file("$buildDir/reports/${project.name}/format-report.txt")
    }
}
