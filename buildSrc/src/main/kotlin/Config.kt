
inline val org.gradle.plugin.use.PluginDependenciesSpec.`ktlint`: org.gradle.plugin.use.PluginDependencySpec
    get() = id(Plugins.ktlint)

object BuildPlugins {
    const val android = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlinExt = "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.kotlin}"
    const val navigation =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val googleServices = "com.google.gms:google-services:${Versions.googleServicesVersion}"
    const val ktlint = "org.jmailen.gradle:kotlinter-gradle:${Versions.ktlint}"
    const val jacoco = "org.jacoco:org.jacoco.core:${Versions.jacoco}"
}

object Libs {
    const val core = "androidx.core:core-ktx:${Versions.androidX}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val constraintLayoutSolver =
        "androidx.constraintlayout:constraintlayout-solver:${Versions.constraintLayout}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}"
    const val multiDex = "androidx.multidex:multidex:${Versions.multidex}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    const val customTabs = "androidx.browser:browser:${Versions.support}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.support}"
    const val design = "com.google.android.material:material:${Versions.design}"

    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val archRoomTime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCoroutines = "androidx.room:room-ktx:${Versions.room}"

    const val lifeCycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycle}"
    const val lifeCycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifeCycle}"
    const val lifeCycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifeCycle}"
    const val lifeCycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"
    const val lifeCycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycle}"
    const val lifeCycleRunTime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}"

    // Kotlin
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"

    /** coroutines-android includes coroutines-core */
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val coroutinesPlay =
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutines}"

    // Dagger
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val daggerAndroidProcessor =
        "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    //Navigation
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationFeature =
        "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}"

    const val dataBinding = "com.android.databinding:compiler:${Versions.dataBinding}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"
    const val paging = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
    const val workManagerRunTime = "androidx.work:work-runtime-ktx:${Versions.workManager}"

    // Network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverterScalars =
        "com.squareup.retrofit2:converter-scalars:${Versions.retrofit}"
    const val retrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKotlinCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val okHttpProfiler =
        "com.itkacher.okhttpprofiler:okhttpprofiler:${Versions.okHttpProfiler}"
    const val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlyticsVersion}"
    const val firebaseCore = "com.google.firebase:firebase-core:${Versions.firebaseVersion}"

    // Time Libs
    const val threeTenABP = "com.jakewharton.threetenabp:threetenabp:${Versions.threeTenABP}"
    const val jodaTimes = "net.danlew:android.joda:${Versions.jodaTimesVersion}"
}

object Plugins {
    const val ktlint = "org.jmailen.kotlinter"
    const val jacoco = "jacoco"
}

object TestLibs {
    const val roomTesting = "androidx.room:room-testing:${Versions.room}"
    const val navigationTest = "androidx.navigation:navigation-testing:${Versions.navigation}"
    const val jUnit = "junit:junit:${Versions.junit}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val testRules = "androidx.test:rules:${Versions.testRunner}"
    const val testOrchestrator = "androidx.test:orchestrator:${Versions.testRunner}"
    const val testExt = "androidx.test.ext:junit:${Versions.testExt}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val lifeCycleTest = "androidx.arch.core:core-testing:${Versions.archVersion}"
    const val workManager = "androidx.work:work-testing:${Versions.workManager}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServerVersion}"
    const val testJodaTime = "joda-time:joda-time:${Versions.testJodaTimeVersion}"
    const val paging = "androidx.paging:paging-common-ktx:${Versions.paging}"
}

object Android {
    const val buildToolsVersion = Versions.buildTools
    const val minSdkVersion = Versions.minSdk
    const val targetSdkVersion = Versions.targetSdk
    const val versionCode = Versions.appVersionCode
    val versionName = Versions.appVersionName
    const val compileVersion = Versions.compileSdk
}

object Versions {
    // Build Config
    const val minSdk = 21
    const val compileSdk = 30
    const val targetSdk = compileSdk
    const val buildTools = "30.0.3"

    // App version
    const val appVersionCode = 1
    const val versionMajor = 0
    const val versionMinor = 0
    const val versionHotFix = 0
    val appVersionName =
        if (versionHotFix != 0) "${versionMajor}.${versionMinor}.${versionHotFix}" else "${versionMajor}.${versionMinor}"

    // Plugins
    const val androidGradlePlugin = "4.1.2"
    const val ktlint = "3.3.0"
    const val jacoco = "0.8.6"

    // Kotlin
    const val kotlin = "1.4.21"
    const val coroutines = "1.4.2"

    // Support Lib
    const val support = "1.3.0-alpha02"
    const val cardView = "1.0.0"
    const val constraintLayout = "1.3.0-rc01"
    const val androidX = "1.5.0-alpha04"
    const val recyclerViewVersion = "1.2.0-alpha03"

    // Libraries
    const val lifeCycle = "2.3.0-rc01"
    const val archVersion = "2.1.0"
    const val room = "2.2.5"
    const val paging = "2.1.2"
    const val retrofit = "2.9.0"
    const val okHttp = "4.7.2"
    const val dagger = "2.31"
    const val dataBinding = "4.1.0-alpha04"
    const val playServices = "16.1.0"
    const val multidex = "2.0.1"
    const val picasso = "2.71828"
    const val navigation = "2.3.2"
    const val workManager = "2.5.0-beta01"
    const val design = "1.3.0-alpha03"
    const val threeTenABP = "1.2.1"
    const val moshi = "1.9.2"
    const val okHttpProfiler = "1.0.7"
    const val gson = "2.8.5"
    const val jodaTimesVersion = "2.10.3"
    const val testJodaTimeVersion = "2.10.5"

    // Testing
    const val junit = "4.13"
    const val testRunner = "1.3.0-alpha05"
    const val testExt = "1.1.2-alpha05"
    const val espresso = "3.3.0-alpha05"
    const val mockito = "3.3.3"
    const val mockitoKotlin = "2.2.0"
    const val timberVersion = "4.7.1"
    const val mockk = "1.10.2"
    const val googleServicesVersion = "4.3.3"
    const val fabricVersion = "1.28.0"
    const val crashlyticsVersion = "2.9.9"
    const val firebaseVersion = "16.0.8"
    const val mockWebServerVersion = "4.5.0"
}

object ApiKeys {
    const val movieDb: String = "aa94b3c2c71ca34288378f22d536ab1f"
}
