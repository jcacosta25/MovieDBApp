plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
}
android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion(Versions.buildTools)
    buildFeatures.dataBinding = true
    defaultConfig {
        applicationId = "io.jcal.theMovie"
        minSdkVersion(Android.minSdkVersion)
        targetSdkVersion(Android.targetSdkVersion)
        versionCode = Android.versionCode
        versionName = Android.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        javaCompileOptions {
            annotationProcessorOptions {
                arguments(mapOf(
                    "room.incremental" to "true",
                    "room.schemaLocation" to "$projectDir/schemas"
                ))
            }
        }
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
    
    kotlinter {
        ignoreFailures = false
        indentSize = 4
        reporters = arrayOf("checkstyle", "plain")
        experimentalRules = true
        disabledRules = arrayOf("import-ordering", "no-wildcard-imports", "final-newline","no-trailing-spaces","indent")
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
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
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