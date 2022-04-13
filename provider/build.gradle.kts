plugins {
    android_library
    kotlin_android
    kotlin_kapt
    kotlin_parcelize
}
android {
    compileSdk = Versions.compileSdk
    buildToolsVersion = Versions.buildTools
    defaultConfig {
        minSdk = Android.minSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    
        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
                arg( "room.incremental" , "true")
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
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
            "indent",
            "experimental:argument-list-wrapping"
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
    
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_11
        sourceCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
        freeCompilerArgs = listOf("-Xjvm-default=enable")
    }
    
    testOptions {
        testOptions {
            reportDir = "$buildDir/reports"
            resultsDir = "$buildDir/test-results"
        }
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
            reportDir = "$buildDir/reports"
            resultsDir = "$buildDir/test-results"
        }
    }
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))
    api(Libs.kotlin)
    api(Libs.kotlinReflect)
    api(Libs.coroutines)
    api(Libs.coroutinesAndroid)
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
