plugins {
    id("com.android.application")
    id("androidx.navigation.safeargs")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion(Versions.buildTools)
    dataBinding.isEnabled = true
    androidExtensions.isExperimental = true
    defaultConfig {
        applicationId = "io.jcal.theMovie"
        minSdkVersion(Android.minSdkVersion)
        targetSdkVersion(Android.targetSdkVersion)
        versionCode = Android.versionCode
        versionName = Android.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "ApiKey", ApiKeys.movieDb)
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    configurations.all {
        resolutionStrategy.force("org.jetbrains:annotations:16.0.1")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
            isTestCoverageEnabled = true
        }
    }

    lintOptions {
        isAbortOnError = true
    }

    repositories {
        flatDir {
            dirs("libs")
        }
    }
    sourceSets {
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
    }

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LGPL2.1")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/LICENSE")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/maven/com.squareup.picasso/picasso/pom.xml")
        exclude("META-INF/maven/com.squareup.picasso/picasso/pom.properties")
        exclude(".readme")
        exclude("asm-license.txt")
        exclude("cglib-license.txt")
        exclude("NOTICE")
        exclude("LICENSE")
        exclude("META-INF/services/javax.annotation.processing.Processor")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/atomicfu.kotlin_module")
    }

    compileOptions {
        setTargetCompatibility(1.8)
        setSourceCompatibility(1.8)
    }
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))
    implementation(project(":movies_provider"))
    implementation(Libs.appCompat)
    implementation(Libs.constraintLayout)
    implementation(Libs.constraintLayoutSolver)
    implementation(Libs.design)
    implementation(Libs.picasso)
    implementation(Libs.multiDex)
    implementation(Libs.navigationFragment)
    implementation(Libs.navigationUi)
    kapt(Libs.daggerCompiler)
    kapt(Libs.daggerAndroidProcessor)
    testImplementation(TestLibs.jUnit)
    androidTestImplementation(TestLibs.testRunner)
    androidTestImplementation(TestLibs.espressoCore)
}