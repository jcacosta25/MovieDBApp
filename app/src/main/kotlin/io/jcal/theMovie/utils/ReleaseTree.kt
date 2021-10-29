package io.jcal.theMovie.utils

import android.util.Log
import timber.log.Timber

class ReleaseTree : Timber.Tree() {
	
	override fun isLoggable(tag: String?, priority: Int): Boolean =
		(priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO).not()
	
	override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
		when (priority) {
			Log.DEBUG -> if (isLoggable(tag, priority)) tag?.let { Timber.tag(it).d(t, message) }
				?: this.d(t, message)
			Log.VERBOSE -> if (isLoggable(tag, priority)) tag?.let { Timber.tag(it).v(t, message) }
				?: this.v(t, message)
			Log.INFO -> if (isLoggable(tag, priority)) tag?.let { Timber.tag(it).i(t, message) }
				?: this.i(t, message)
			Log.WARN -> tag?.let { Timber.tag(it).w(t, message) } ?: this.w(t, message)
			Log.ERROR -> tag?.let { Timber.tag(it).e(t, message) } ?: this.e(t, message)
			else -> tag?.let { Timber.tag(it).wtf(t, message) } ?: this.wtf(t, message)
		}
	}
}
