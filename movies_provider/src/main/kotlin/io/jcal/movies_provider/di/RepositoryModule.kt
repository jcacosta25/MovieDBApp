package io.jcal.movies_provider.di

import android.content.Context
import dagger.Module
import dagger.Provides
import io.jcal.movies_provider.domain.interactor.base.NetworkUtil
import io.jcal.movies_provider.repository.Repository
import io.jcal.movies_provider.repository.RepositoryImpl
import io.jcal.movies_provider.repository.api.MovieDBService
import io.jcal.movies_provider.repository.datasource.CloudDataSource
import io.jcal.movies_provider.repository.datasource.CloudDataSourceImpl
import io.jcal.movies_provider.repository.datasource.DiskDataSource
import io.jcal.movies_provider.repository.datasource.DiskDataSourceImpl
import io.jcal.movies_provider.repository.db.MovieDBDataBase

@Module
class RepositoryModule {

    @Provides
    fun providesDiskDataSource(dataBase: MovieDBDataBase): DiskDataSource =
        DiskDataSourceImpl(dataBase)

    @Provides
    fun provideCloudDataSource(api: MovieDBService): CloudDataSource = CloudDataSourceImpl(api)

    @Provides
    fun provideNetworkUtils(context: Context): NetworkUtil = NetworkUtil(context)

    @Provides
    fun provideRepository(repository: RepositoryImpl): Repository = repository
}