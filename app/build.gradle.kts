plugins {
	android_application
	kotlin_android
	kotlin_kapt
	kotlin_parcelize
	navigation_safeargs
}
android {
	compileSdk = Versions.compileSdk
	buildToolsVersion = Versions.buildTools
	buildFeatures.dataBinding = true
	defaultConfig {
		applicationId = "io.jcal.theMovie"
		minSdk = Android.minSdkVersion
		versionCode = Android.versionCode
		versionName = Android.versionName
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
	}
	
	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
			multiDexEnabled = true
		}
		getByName("debug") {
			isMinifyEnabled = false
			isTestCoverageEnabled = true
			multiDexEnabled = true
		}
	}
	
	lintOptions.apply {
		isAbortOnError = true
	}
	
	kotlinter {
		ignoreFailures = false
		indentSize = 4
		reporters = arrayOf("checkstyle", "plain")
		experimentalRules = true
		disabledRules = arrayOf(
			"import-ordering",
			"no-wildcard-imports",
			"final-newline",
			"no-trailing-spaces",
			"indent"
		)
	}
	
	sourceSets {
		getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
		getByName("main").apply {
			java.srcDirs("src/main/kotlin")
			jniLibs.srcDir("libs")
		}
		getByName("test").java.srcDirs("src/test/kotlin")
	}
	
	packagingOptions {
		jniLibs {
			excludes.addAll(
				listOf(
					"META-INF/DEPENDENCIES",
					"META-INF/LGPL2.1",
					"META-INF/ASL2.0",
					"META-INF/LICENSE",
					"META-INF/NOTICE",
					"META-INF/NOTICE.txt",
					"META-INF/LICENSE.txt",
					"META-INF/maven/com.squareup.picasso/picasso/pom.xml",
					"META-INF/maven/com.squareup.picasso/picasso/pom.properties",
					".readme",
					"asm-license.txt",
					"cglib-license.txt",
					"NOTICE",
					"LICENSE",
					"META-INF/services/javax.annotation.processing.Processor",
					"META-INF/notice.txt",
					"META-INF/ASL2.0",
					"META-INF/atomicfu.kotlin_module"
				)
			)
		}
	}
	
	compileOptions {
		targetCompatibility = JavaVersion.VERSION_11
		sourceCompatibility = JavaVersion.VERSION_11
	}
	kotlinOptions {
		jvmTarget = JavaVersion.VERSION_11.toString()
	}
}

dependencies {
	implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))
	implementation(project(":movies_provider"))
	implementation(Libs.appCompat)
	implementation(Libs.kotlin)
	implementation(Libs.kotlinReflect)
	implementation(Libs.constraintLayout)
	implementation(Libs.constraintLayoutSolver)
	implementation(Libs.design)
	implementation(Libs.picasso)
	implementation(Libs.multiDex)
	implementation(Libs.paging)
	implementation(Libs.navigationFragment)
	implementation(Libs.navigationUi)
	kapt(Libs.daggerCompiler)
	kapt(Libs.daggerAndroidProcessor)
	kapt(Libs.lifeCycleCompiler)
	testImplementation(TestLibs.jUnit)
	androidTestImplementation(TestLibs.testRunner)
	androidTestImplementation(TestLibs.espressoCore)
}

tasks.check {
	dependsOn("installKotlinterPrePushHook")
}