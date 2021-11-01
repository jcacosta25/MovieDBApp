object Libs {
	const val core = "androidx.core:core-ktx:${Versions.core}"
	const val constraintLayout =
		"androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
	const val constraintLayoutSolver =
		"androidx.constraintlayout:constraintlayout-solver:${Versions.constraintLayoutSolver}"
	const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}"
	const val multiDex = "androidx.multidex:multidex:${Versions.multidex}"
	const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
	const val customTabs = "androidx.browser:browser:1.3.0"
	const val appCompat = "androidx.appcompat:appcompat:${Versions.support}"
	const val design = "com.google.android.material:material:${Versions.design}"
	
	const val picasso = "com.squareup.picasso:picasso:2.71828"
	const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
	const val archRoomTime = "androidx.room:room-runtime:${Versions.room}"
	const val roomCoroutines = "androidx.room:room-ktx:${Versions.room}"
	
	const val lifeCycleExtensions =
		"androidx.lifecycle:lifecycle-extensions:2.2.0"
	const val lifeCycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifeCycle}"
	const val lifeCycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifeCycle}"
	const val lifeCycleViewModel =
		"androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"
	const val lifeCycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycle}"
	const val lifeCycleRunTime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}"
	
	// Kotlin
	const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
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
	const val firebaseBoM = "com.google.firebase:firebase-bom:${Versions.firebaseVersion}"
	const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
	const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
	const val firebaseMessaging = "com.google.firebase:firebase-messaging-ktx"
	
	// Time Libs
	const val threeTenABP = "com.jakewharton.threetenabp:threetenabp:${Versions.threeTenABP}"
	const val jodaTimes = "net.danlew:android.joda:${Versions.jodaTimesVersion}"
}
