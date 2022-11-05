package io.jcal.theMovie

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp
import io.jcal.theMovie.utils.ReleaseTree
import timber.log.Timber

@HiltAndroidApp
class MovieDBApp : Application() {
	
	override fun onCreate() {
		super.onCreate()
		AndroidThreeTen.init(this)
		if (BuildConfig.DEBUG) {
			Timber.plant(object : Timber.DebugTree() {
				override fun createStackElementTag(element: StackTraceElement): String? =
					super.createStackElementTag(element).plus(':').plus(element.lineNumber)
			})
		} else {
			Timber.plant(ReleaseTree())
		}
	}
}
