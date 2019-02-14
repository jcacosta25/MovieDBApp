package io.jcal.theMovie

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.jcal.theMovie.di.components.DaggerApplicationComponent
import io.jcal.theMovie.utils.ReleaseTree
import timber.log.Timber

class MovieDBApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(object : timber.log.Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? =
                    super.createStackElementTag(element).plus(':').plus(element.lineNumber)
            })
        } else {
            Timber.plant(ReleaseTree())
        }
    }
}