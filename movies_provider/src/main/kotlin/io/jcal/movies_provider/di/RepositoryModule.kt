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
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesDiskDataSource(dataBase: MovieDBDataBase): DiskDataSource =
        DiskDataSourceImpl(dataBase)

    @Provides
    @Singleton
    fun provideCloudDataSource(api: MovieDBService): CloudDataSource = CloudDataSourceImpl(api)

    @Provides
    @Singleton
    fun provideNetworkUtils(context: Context): NetworkUtil = NetworkUtil(context)

    @Provides
    @Singleton
    fun provideRepository(repository: RepositoryImpl): Repository = repository
}