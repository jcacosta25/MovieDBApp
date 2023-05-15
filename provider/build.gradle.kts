plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}
android {
    compileSdk = 33
    buildToolsVersion = "30.0.3"
    defaultConfig {
        minSdk = 23
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
                arg("room.incremental", "true")
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

    kotlinter {
        ignoreFailures = false
        reporters = arrayOf("checkstyle", "plain")
        experimentalRules = true
        disabledRules = arrayOf(
            "import-ordering",
            "no-wildcard-imports",
            "final-newline",
            "no-trailing-spaces",
            "indent",
            "experimental:argument-list-wrapping",
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
        targetCompatibility = JavaVersion.VERSION_17
        sourceCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
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
    namespace = "io.jcal.provider"
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.21")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.21")
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    api("androidx.lifecycle:lifecycle-common-java8:2.6.1")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    api("androidx.lifecycle:lifecycle-livedata-core-ktx:2.6.1")
    api("androidx.core:core-ktx:1.10.1")
    api("com.jakewharton.timber:timber:5.0.1")
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.5.0")
    api("com.jakewharton.threetenabp:threetenabp:1.4.0")
    api("com.google.code.gson:gson:2.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.6")
    api("androidx.room:room-ktx:2.5.1")
    kapt("androidx.room:room-compiler:2.5.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.room:room-testing:2.5.1")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("io.mockk:mockk:1.13.5")
    androidTestImplementation("io.mockk:mockk-android:1.13.5")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

kapt {
    correctErrorTypes = true
}
