plugins {
    id("com.android.library")
    kotlin("kapt")
    kotlin("android")
    kotlin("android.extensions")
}
apply {
    plugin("kotlin-android-extensions")
}
android {
    compileSdkVersion(Versions.compileSdk)
    defaultConfig {
        minSdkVersion(Android.minSdkVersion)
        targetSdkVersion(Android.targetSdkVersion)
        versionCode = Android.versionCode
        versionName = Android.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String","ApiKey",ApiKeys.movieDb)
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
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))
    api(Libs.kotlin)
    api(Libs.kotlinReflect)
    implementation(Libs.appCompat)
    implementation(Libs.retrofit)
    implementation(Libs.retrofitConverterMoshi)
    implementation(Libs.okHttp)
    implementation(Libs.okHttpInterceptor)
    api(Libs.lifeCycleExtensions)
    api(Libs.lifeCycleCommon)
    api(Libs.core)
    implementation(Libs.archRoomTime)
    testImplementation(TestLibs.roomTesting)
    testImplementation(TestLibs.lifeCycleTest)
    testImplementation(TestLibs.mockk)
    androidTestImplementation(TestLibs.mockkAndroid)
    api(Libs.timber)
    api(Libs.dagger)
    api(Libs.daggerAndroid)
    api(Libs.daggerAndroidSupport)
    api(Libs.threeTenABP)
    testImplementation(TestLibs.jUnit)
    androidTestImplementation(TestLibs.testRunner)
    androidTestImplementation(TestLibs.espressoCore)
    implementation(Libs.moshi)
    kapt(Libs.moshiKotlinCodeGen)
    implementation(Libs.okHttpProfiler)
}
repositories {
    mavenCentral()
}