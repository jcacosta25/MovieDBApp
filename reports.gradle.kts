

val jacocoIgnoreList = listOf(name)

tasks.all {
	when (this) {
		is JacocoTaskExtension -> {
			isIncludeNoLocationClasses = true
		}
	}
}
subprojects {
	apply(plugin = "org.jmailen.kotlinter")
	
//	tasks.create("ktLint", LintTask::class) {
//		group = "verification"
//		source(files("src"))
//	}
//
//	tasks.create("ktFormat", FormatTask::class) {
//		group = "formatting"
//		source(files("src"))
//	}
	
	
	
	afterEvaluate {
		if (jacocoIgnoreList.contains(this.name).not() && childProjects.isEmpty()) {
			apply(plugin = "jacoco")
			
			// Change to minumum code coverage
			val defaultMinimumCoverage = 0.8.toBigDecimal()
			val threshold =
				if (extra.has(
						"jacocoCoverageThreshold"
					)
				) extra["jacocoCoverageThreshold"] as BigDecimal?
					?: defaultMinimumCoverage else defaultMinimumCoverage
			
			extensions.apply {
				this.getByType(JacocoPluginExtension::class.java).apply {
					this.toolVersion = "0.8.8"
					reportsDirectory.set(layout.buildDirectory.dir("${project.buildDir}/reports/jacoco"))
				}
			}
			
			val excludes = listOf(
				"**/*\$run$1.class",
				"android/databinding/**/*.class",
				"**/android/databinding/*Binding.class",
				"**/android/databinding/*",
				"**/androidx/databinding/*",
				"**/*Test*.*",
				"android/**/*.*",
				"**/R.class",
				"**/R$*.class",
				"**/*Fragment.*",
				"**/*Fragment*.*",
				"**/*Activity*.*",
				"**/*Adapter*.*",
				"**/*Activity.*",
				"**/Dagger*Component*.class",
				"**/Dagger*Component\$Builder.class",
				"**/Dagger*Subcomponent*.class",
				"**/*Subcomponent\$Builder.class",
				"**/*Module_*Factory.class",
				"**/di/module/*",
				"**/*_*Factory*.*",
				"**/androidTest/**",
				"**/*Component*.*",
				"**/*DI.*",
				"**/*DI*.*",
				"**/*MapperImpl*.*",
				"**/*\$ViewInjector*.*",
				"**/*\$ViewBinder*.*",
				"**/BuildConfig.*",
				"**/*Component*.*",
				"**/*BR*.*",
				"**/Manifest*.*",
				"**/*\$Lambda$*.*",
				"**/*Companion*.*",
				"**/*Module*.*",
				"**/*Dagger*.*",
				"**/*Hilt*.*",
				"**/*App*.*",
				"**/*MembersInjector*.*",
				"**/*_MembersInjector.class",
				"**/*_Factory*.*",
				"**/*_Provide*Factory*.*",
				"**/*Extensions*.*",
				"**/*\$Result.*",
				"**/*\$Result\$*.*",
				"$buildDir/generated/source/kapt/**"
			)
			
			@Suppress("UnstableApiUsage")
			tasks.create("jacocoTestReport", JacocoReport::class) {
				dependsOn("testDebugUnitTest")
				group = "reporting"
				
				classDirectories.setFrom(
					fileTree(
						"dir" to "$buildDir/tmp/kotlin-classes/debug",
						"excludes" to excludes
					).plus(
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
				
				sourceDirectories.setFrom(
					"${project.projectDir}/src/main/java",
					"${project.projectDir}/src/main/kotlin"
				)
			}
			
			
			tasks.create("jacocoTestCoverageVerification", JacocoCoverageVerification::class) {
				dependsOn("testDebugUnitTest")
				group = "verification"
				violationRules {
					rule {
						// TODO: Remove when the minumum coverage of all projects is the one that we agreed
						isFailOnViolation = false
						limit {
							minimum = threshold
						}
					}
				}
				
				classDirectories.setFrom(
					fileTree(
						"dir" to "${project.buildDir}/tmp/kotlin-classes/debug",
						"excludes" to excludes
					).plus(
						fileTree(
							"dir" to "${project.buildDir}/intermediates/classes/debug",
							"excludes" to excludes
						)
					)
						.plus(project.fileTree("build/classes/kotlin/main"))
						.plus(
							fileTree(
								"dir" to "${project.buildDir}/intermediates/classes/debug/com",
								"excludes" to excludes
							)
						)
						.plus(
							fileTree(
								"dir" to "${project.buildDir}/tmp/kotlin-classes/debug/com",
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
				
				sourceDirectories.setFrom(
					"${project.projectDir}/src/main/java",
					"${project.projectDir}/src/main/kotlin"
				)
			}
		}
	}
}

tasks {
	register("jacocoFullReport", JacocoReport::class) {
		group = "Coverage reports"
		val projects = subprojects
		
		// Here we depend on the jacocoReport task that we created before
		val subTasks =
			projects.mapNotNull { it.tasks.findByName("jacocoTestReport") as JacocoReport? }
		dependsOn(subTasks)
		val verificationTasks =
			projects.mapNotNull {
				it.tasks.findByName(
					"jacocoTestCoverageVerification"
				) as JacocoCoverageVerification?
			}
		dependsOn(verificationTasks)
		val subSourceDirs = subTasks.map { files(it.sourceDirectories) }
		additionalSourceDirs.setFrom(subSourceDirs)
		sourceDirectories.setFrom(subSourceDirs)
		
		classDirectories.setFrom(subTasks.map { files(it.classDirectories) })
		executionData.setFrom(subTasks.map { files(it.executionData) })
		
//		reports {
//			html.isEnabled = true
//			html.destination = file("$buildDir/reports/jacoco/html")
//
//			xml.isEnabled = true
//			xml.destination = file("$buildDir/reports/jacoco/jacocoFullReport.xml")
//		}
		
		doFirst {
			executionData.setFrom(files(executionData.filter { it.exists() }))
		}
	}
	
	tasks.findByName("check")?.apply {
		dependsOn("installKotlinterPrePushHook")
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
//		dependsOn(
//			runAppTask(taskName = "ktFormat")
//		)
	}
	
	tasks.create("projectLinter") {
		group = "verification"
		description = "Runs Code Lint Checker on all project modules"
//		dependsOn(
//			runAppTask(taskNames = mutableListOf("lint", "ktLint"))
//		)
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