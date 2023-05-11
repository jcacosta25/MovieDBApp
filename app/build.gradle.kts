plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}
android {
    compileSdk = 33
    buildToolsVersion = "30.0.3"
    buildFeatures.compose = true
    defaultConfig {
        applicationId = "io.jcal.theMovie"
        minSdk = 24
        versionCode = 1
        versionName = "1.0.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
	
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            multiDexEnabled = true
        }
        getByName("debug") {
            isMinifyEnabled = false
            isTestCoverageEnabled = true
            multiDexEnabled = true
        }
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
	
    packagingOptions {
        jniLibs {
            excludes.addAll(
                listOf(
                    "META-INF/DEPENDENCIES",
                    "META-INF/LGPL2.1",
                    "META-INF/ASL2.0",
                    "META-INF/LICENSE",
                    "META-INF/NOTICE",
                    "META-INF/NOTICE.txt",
                    "META-INF/LICENSE.txt",
                    "META-INF/maven/com.squareup.picasso/picasso/pom.xml",
                    "META-INF/maven/com.squareup.picasso/picasso/pom.properties",
                    ".readme",
                    "asm-license.txt",
                    "cglib-license.txt",
                    "NOTICE",
                    "LICENSE",
                    "META-INF/services/javax.annotation.processing.Processor",
                    "META-INF/notice.txt",
                    "META-INF/ASL2.0",
                    "META-INF/atomicfu.kotlin_module",
                    "/META-INF/{AL2.0,LGPL2.1}",
                ),
            )
        }
    }
	
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
	
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_17
        sourceCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    namespace = "io.jcal.theMovie"
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))
    implementation(project(":provider"))
    implementation("com.github.bumptech.glide:compose:1.0.0-alpha.1")
    implementation("com.android.support:multidex:2.0.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.21")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.21")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.constraintlayout:constraintlayout-solver:2.0.4")
    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")
    implementation("androidx.paging:paging-compose:1.0.0-alpha19")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("io.coil-kt:coil-compose:2.0.0-rc03")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation("androidx.compose.runtime:runtime-livedata:1.4.3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
    implementation("androidx.compose.material:material:1.4.3")
    implementation("androidx.compose.material:material-icons-core:1.4.3")
    implementation("androidx.compose.material:material-icons-extended:1.4.3")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.compose.material3:material3:1.2.0-alpha01")
    implementation("androidx.compose.material3:material3-window-size-class:1.2.0-alpha01")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
    implementation("com.google.dagger:hilt-android:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.5.0")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.4.3")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.3")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.4.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

kapt {
    correctErrorTypes = true
}
