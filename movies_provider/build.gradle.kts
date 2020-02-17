
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
}
android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion(Versions.buildTools)
    defaultConfig {
        minSdkVersion(Android.minSdkVersion)
        targetSdkVersion(Android.targetSdkVersion)
        versionCode = Android.versionCode
        versionName = Android.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = mapOf("room.incremental" to "true","room.schemaLocation" to "$projectDir/schemas")
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
    sourceSets {
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
    }

    compileOptions {
        setTargetCompatibility(1.8)
        setSourceCompatibility(1.8)
    }
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))
    api(Libs.kotlin)
    api(Libs.kotlinReflect)
    api(Libs.coroutines)
    api(Libs.coroutinesAndroid)
    api(Libs.lifeCycleExtensions)
    api(Libs.lifeCycleCommon)
    api(Libs.lifeCycleViewModel)
    api(Libs.lifeCycleLiveData)
    api(Libs.core)
    api(Libs.timber)
    api(Libs.dagger)
    api(Libs.daggerAndroid)
    api(Libs.daggerAndroidSupport)
    api(Libs.threeTenABP)
    api(Libs.gson)
    api(Libs.okHttpProfiler)
    api(Libs.appCompat)
    api(Libs.retrofit)
    api(Libs.retrofitConverterGson)
    api(Libs.okHttp)
    api(Libs.okHttpInterceptor)
    api(Libs.roomCoroutines)
    kapt(Libs.daggerAndroidProcessor)
    kapt(Libs.daggerCompiler)
    kapt(Libs.roomCompiler)
    testImplementation(TestLibs.jUnit)
    testImplementation(TestLibs.roomTesting)
    testImplementation(TestLibs.lifeCycleTest)
    testImplementation(TestLibs.mockk)
    androidTestImplementation(TestLibs.mockkAndroid)
    androidTestImplementation(TestLibs.testRunner)
    androidTestImplementation(TestLibs.espressoCore)
}