package io.jcal.theMovie.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.jcal.theMovie.presentation.ui.movies.MovieDetailFragment
import io.jcal.theMovie.presentation.ui.home.PopularMoviesFragment
import io.jcal.theMovie.presentation.ui.home.PopularShowsFragment
import io.jcal.theMovie.presentation.ui.series.ShowDetailFragment

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MoviesDBFeatureModule::class])
    abstract fun popularMoviesFragment(): PopularMoviesFragment

    @ContributesAndroidInjector(modules = [MoviesDBFeatureModule::class])
    abstract fun popularShowsFragment(): PopularShowsFragment

    @ContributesAndroidInjector(modules = [MoviesDBFeatureModule::class])
    abstract fun movieDetailFragment(): MovieDetailFragment

    @ContributesAndroidInjector(modules = [MoviesDBFeatureModule::class])
    abstract fun showDetailFragment(): ShowDetailFragment
}
