package io.jcal.theMovie.di.components

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import io.jcal.theMovie.MovieDBApp
import io.jcal.theMovie.di.module.ActivityBindingModule
import io.jcal.theMovie.di.module.core.ApplicationModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        AndroidInjectionModule::class,
        ActivityBindingModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<MovieDBApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun context(): Context
}
