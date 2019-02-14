object BuildPlugins {
    const val android = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlinExt = "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.kotlin}"
    const val navigation =
        "android.arch.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

object Libs {
    const val core = "androidx.core:core-ktx:${Versions.androidX}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val constraintLayoutSolver =
        "androidx.constraintlayout:constraintlayout-solver:${Versions.constraintLayout}"
    const val multiDex = "androidx.multidex:multidex:${Versions.multidex}"
    const val cardView = "androidx.cardview:cardview:${Versions.support}"
    const val customTabs = "androidx.browser:browser:${Versions.support}"
    const val supportV4 = "androidx.legacy:legacy-support-v4:${Versions.support}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.support}"
    const val design = "com.google.android.material:material:${Versions.design}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val archRoomTime = "androidx.room:room-runtime:${Versions.room}"

    const val lifeCycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycle}"
    const val lifeCycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifeCycle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val daggerAndroidProcessor =
        "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val navigationFragment =
        "android.arch.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "android.arch.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val dataBinding = "com.android.databinding:compiler:${Versions.dataBinding}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"
    const val pagingRuntime = "android.arch.paging:runtime:${Versions.paging}"
    const val workManagerRunTime = "android.arch.work:work-runtime:${Versions.workManager}"
    const val threeTenABP = "com.jakewharton.threetenabp:threetenabp:${Versions.threeTenABP}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKotlinCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val okHttpProfiler =
        "com.itkacher.okhttpprofiler:okhttpprofiler:${Versions.okHttpProfiler}"
}

object TestLibs {
    const val roomTesting = "androidx.room:room-testing:${Versions.room}"
    const val navigationTest = "android.arch.navigation:navigation-testing:${Versions.navigation}"
    const val jUnit = "junit:junit:${Versions.junit}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val testRules = "androidx.test:rules:${Versions.testRunner}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val lifeCycleTest = "androidx.arch.core:core-testing:${Versions.lifeCycle}"
    const val workManager = "android.arch.work:work-testing:${Versions.workManager}"

}

object Android {
    const val buildToolsVersion = Versions.buildTools
    const val minSdkVersion = Versions.minSdk
    const val targetSdkVersion = Versions.targetSdk
    const val versionCode = Versions.appVersionCode
    const val versionName = Versions.appVersionName
}


object Versions {
    // Build Config
    const val minSdk = 21
    const val compileSdk = 28
    const val targetSdk = compileSdk
    const val buildTools = "28.0.3"

    // App version
    const val appVersionCode = 1
    const val appVersionName = "1.0.0"

    // Plugins
    const val androidGradlePlugin = "3.3.0"

    // Kotlin
    const val kotlin = "1.3.21"

    // Support Lib
    const val support = "1.0.0-beta01"
    const val constraintLayout = "1.1.2"
    const val androidX = "1.1.0-alpha04"

    // Libraries
    const val lifeCycle = "2.0.0"
    const val room = "2.1.0-alpha04"
    const val paging = "2.1.0"
    const val retrofit = "2.5.0"
    const val okHttp = "3.12.1"
    const val dagger = "2.21"
    const val dataBinding = "3.1.3"
    const val playServices = "16.1.0"
    const val multidex = "2.0.0"
    const val picasso = "2.71828"
    const val navigation = "1.0.0-alpha11"
    const val workManager = "1.0.0-beta03"
    const val design = "1.0.0-beta01"
    const val threeTenABP = "1.1.1"
    const val moshi = "1.8.0"
    const val okHttpProfiler = "1.0.5"
    const val gson = "2.8.5"

    // Testing
    const val junit = "4.12"
    const val testRunner = "1.1.0-alpha4"
    const val espresso = "3.1.0-alpha4"
    const val mockito = "2.24.0"
    const val mockitoKotlin = "2.0.0"
    const val timberVersion = "4.7.1"
    const val mockk = "1.9.kotlin12"

}
