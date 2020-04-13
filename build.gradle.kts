import org.jmailen.gradle.kotlinter.tasks.FormatTask
import org.jmailen.gradle.kotlinter.tasks.LintTask

plugins {
    application
    jacoco
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
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin(Plugins.ktlint)
        plugin(Plugins.jacoco)
    }
    jacoco {
        toolVersion = Versions.jacoco
        reportsDir = file("$buildDir/reports/jacoco")
    }

    @Suppress("UnstableApiUsage")
    tasks.create("jacocoTestReport", JacocoReport::class) {
        dependsOn("testDebugUnitTest")
        reports {
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
                .plus(
                    fileTree(
                        "dir" to "$buildDir/intermediates/classes/debug",
                        "excludes" to excludes
                    )
                )
                .plus(fileTree("build/classes/kotlin/main"))
                .plus(
                    fileTree(
                        "dir" to "$buildDir/intermediates/classes/debug/com",
                        "excludes" to excludes
                    )
                )
                .plus(
                    fileTree(
                        "dir" to "$buildDir/tmp/kotlin-classes/debug/com",
                        "excludes" to excludes
                    )
                )
        )
        executionData.setFrom(
            fileTree(
                "dir" to project.buildDir,
                "include" to listOf("**/*.exec", "**/*.ec")
            )
        )

        sourceDirectories.setFrom("${project.projectDir}/src/main/java")
    }

    tasks.create("jacocoTestCoverageVerification", JacocoCoverageVerification::class) {
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

    tasks.create("ktLint", LintTask::class) {
        group = "verification"
        source(files("src"))
        reports = mapOf(
            "plain" to file("$buildDir/reports/${project.name}/lint-report.txt"),
            "json" to file("$buildDir/reports/${project.name}/lint-report.json"),
            "html" to file("$buildDir/reports/${project.name}/lint-report.html")
        )
        setDisabledRules(arrayOf("import-ordering"))
    }

    tasks.create("ktFormat", FormatTask::class) {
        group = "formatting"
        source(files("src"))
        report = file("$buildDir/reports/${project.name}/format-report.txt")
        setDisabledRules(arrayOf("import-ordering"))
    }

    fun taskName(
        taskName: String = "",
        taskNames: MutableList<String> = mutableListOf()
    ): String = if (taskNames.isEmpty()) {
        taskNames.add(taskName)
        taskName
    } else {
        var name = ""
        taskNames.forEach {
            name = if (name.isEmpty()) {
                it
            } else {
                "_$it"
            }
        }
        name
    }

    fun runModuleTask(
        module: String,
        taskName: String = "",
        taskNames: MutableList<String> = mutableListOf()
    ): Task {
        return tasks.create("${module}_${taskName(taskName, taskNames)}") {
            val project = subprojects.asSequence().filter { it.name == module }.first()
            project.childProjects.forEach { (_, module) ->
                taskNames.forEach { taskName ->
                    val task = module.getTasksByName(taskName, true)
                    this.dependsOn(task)
                }
            }
        }
    }

    fun runAppTask(taskName: String = "", taskNames: MutableList<String> = mutableListOf()): Task {
        if (taskNames.isEmpty()) {
            taskNames.add(taskName)
        }
        return tasks.create("app_${taskName(taskName, taskNames)}") {
            taskNames.forEach { taskName ->
                this.dependsOn(":app:$taskName")
            }
        }
    }

    tasks.create("projectClean") {
        group = "cleanup"
        description = "Runs all clean on all project modules"
        dependsOn(
            runAppTask(taskName = "clean")
        )
    }

    tasks.create("projectFormat") {
        group = "formatting"
        description = "Runs Code format on all project modules"
        dependsOn(
            runAppTask(taskName = "ktFormat")
        )
    }

    tasks.create("projectLinter") {
        group = "verification"
        description = "Runs Code Lint Checker on all project modules"
        dependsOn(
            runAppTask(taskNames = mutableListOf("lint", "ktLint"))
        )
    }

    tasks.create("projectUnitTest") {
        group = "verification"
        description = "Runs Unit Test on all project modules"
        dependsOn(
            runAppTask(taskName = "testDebugUnitTest")
        )
    }

    tasks.create("projectJacocoReport") {
        group = "verification"
        description = "Runs Jacoco Test Report on all project modules"
        dependsOn(
            runAppTask(taskName = "jacocoTestReport")
        )
    }

    tasks.create("projectTestCoverageVerification") {
        group = "verification"
        description = "Runs Jacoco Test Report on all project modules"
        dependsOn(
            runAppTask(taskName = "jacocoTestCoverageVerification")
        )
    }
}
