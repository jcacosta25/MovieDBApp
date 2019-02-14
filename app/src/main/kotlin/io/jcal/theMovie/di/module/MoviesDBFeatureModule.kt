package io.jcal.theMovie.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.jcal.movies_provider.di.NetworkModule
import io.jcal.movies_provider.di.RepositoryModule
import io.jcal.movies_provider.di.StorageModule
import io.jcal.theMovie.di.scopes.ViewModelKey
import io.jcal.theMovie.presentation.viewmodel.MoviesViewModel
import io.jcal.theMovie.utils.ViewModelFactoryProvider

@Module(includes = [RepositoryModule::class, NetworkModule::class, StorageModule::class])
abstract class MoviesDBFeatureModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactoryProvider): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(viewModel: MoviesViewModel): ViewModel
}