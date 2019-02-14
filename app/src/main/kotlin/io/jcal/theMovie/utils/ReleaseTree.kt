package io.jcal.theMovie.utils

import android.util.Log
import timber.log.Timber

class ReleaseTree : Timber.Tree() {

    override fun isLoggable(tag: String?, priority: Int): Boolean =
        (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO).not()

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if(isLoggable(tag,priority)) {

        }
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}