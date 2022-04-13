inline val org.gradle.plugin.use.PluginDependenciesSpec.ktlint: org.gradle.plugin.use.PluginDependencySpec
    get() = id("org.jmailen.kotlinter")

inline val org.gradle.plugin.use.PluginDependenciesSpec.android_application: org.gradle.plugin.use.PluginDependencySpec
    get() = id("com.android.application")

inline val org.gradle.plugin.use.PluginDependenciesSpec.android_library: org.gradle.plugin.use.PluginDependencySpec
    get() = id("com.android.library")

inline val org.gradle.plugin.use.PluginDependenciesSpec.kotlin_android: org.gradle.plugin.use.PluginDependencySpec
    get() = id("kotlin-android")

inline val org.gradle.plugin.use.PluginDependenciesSpec.kotlin_kapt: org.gradle.plugin.use.PluginDependencySpec
    get() = id("kotlin-kapt")

inline val org.gradle.plugin.use.PluginDependenciesSpec.kotlin_parcelize: org.gradle.plugin.use.PluginDependencySpec
    get() = id("kotlin-parcelize")

inline val org.gradle.plugin.use.PluginDependenciesSpec.navigation_safeargs: org.gradle.plugin.use.PluginDependencySpec
    get() = id("androidx.navigation.safeargs.kotlin")

inline val org.gradle.plugin.use.PluginDependenciesSpec.resources_remover: org.gradle.plugin.use.PluginDependencySpec
    get() = id("com.github.konifar.gradle.unused-resources-remover")
    


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
    const val compileSdk = 31
    const val targetSdk = compileSdk
    const val buildTools = "31.0.0"
    
    // App version
    const val appVersionCode = 1
    private const val versionMajor = 0
    private const val versionMinor = 0
    private const val versionHotFix = 0
    val appVersionName = listOfNotNull(versionMajor, versionMinor, versionHotFix.takeIf { it != 0 }).joinToString(".")
    
    // Plugins
    const val ktlint = "3.6.0"
    const val jacoco = "0.8.7"
    const val androidGradlePlugin = "7.1.2"
    
    // Kotlin
    const val kotlin = "1.6.10"
    const val coroutines = "1.6.1"
    
    // Support Lib
    const val support = "1.6.0-alpha01"
    const val cardView = "1.0.0"
    const val constraintLayout = "2.1.3"
    const val constraintLayoutSolver = "2.0.4"
    const val core = "1.9.0-alpha02"
    
    // Libraries
    const val recyclerViewVersion = "1.3.0-alpha02"
    const val multidex = "2.0.1"
    const val lifeCycle = "2.5.0-alpha06"
    const val archVersion = "2.1.0"
    const val room = "2.5.0-alpha01"
    const val paging = "3.1.1"
    const val retrofit = "2.9.0"
    const val okHttp = "5.0.0-alpha.6"
    const val dagger = "2.41"
    const val dataBinding = "4.1.0-alpha04"
    const val playServices = "16.1.0"
    const val picasso = "2.71828"
    const val navigation = "2.5.0-alpha04"
    const val compose = "1.1.1"
    const val workManager = "2.8.0-alpha02"
    const val design = "1.6.0-beta01"
    const val threeTenABP = "1.4.0"
    const val moshi = "1.13.0"
    const val okHttpProfiler = "1.0.7"
    const val gson = "2.9.0"
    const val jodaTimesVersion = "2.10.14"
    const val timberVersion = "5.0.1"
    const val crashlyticsVersion = "18.2.3"
    const val firebaseVersion = "29.3.0"
    
    // Testing
    const val junit = "4.13.2"
    const val testRunner = "1.4.0"
    const val testExt = "1.1.3"
    const val espresso = "3.4.0"
    const val mockito = "4.0.0"
    const val mockitoKotlin = "2.2.0"
    const val mockk = "1.12.3"
    const val mockWebServerVersion = "4.9.2"
    const val testJodaTimeVersion = "2.10.13"
}

object ApiKeys {
    const val movieDb: String = "aa94b3c2c71ca34288378f22d536ab1f"
}
