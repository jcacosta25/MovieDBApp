package io.jcal.theMovie.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.jcal.theMovie.presentation.ui.MainActivity

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MoviesDBFeatureModule::class])
    abstract fun mainActivity(): MainActivity

}