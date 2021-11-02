object BuildPlugins {
	const val android = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
	const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
	const val kotlinExt = "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.kotlin}"
	const val navigation =
		"androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
	const val googleServices = "com.google.gms:google-services:4.3.10"
	const val ktlint = "org.jmailen.gradle:kotlinter-gradle:${Versions.ktlint}"
	const val jacoco = "org.jacoco:org.jacoco.core:${Versions.jacoco}"
	const val resourcesRemover = "gradle.plugin.com.github.konifar.gradle:plugin:0.3.3"
}
