package io.jcal.movies_provider.di

import android.content.Context
import dagger.Module
import dagger.Provides
import io.jcal.movies_provider.domain.interactor.base.NetworkUtil
import io.jcal.movies_provider.repository.MDBRepository
import io.jcal.movies_provider.repository.MDBRepositoryImpl
import io.jcal.movies_provider.repository.api.MovieDBService
import io.jcal.movies_provider.repository.datasource.CloudDataSource
import io.jcal.movies_provider.repository.datasource.CloudDataSourceImpl
import io.jcal.movies_provider.repository.datasource.DiskDataSource
import io.jcal.movies_provider.repository.datasource.DiskDataSourceImpl
import io.jcal.movies_provider.repository.db.MovieDBDataBase
import io.jcal.movies_provider.repository.mapper.DataMapper

@Module
class RepositoryModule {

    @Provides
    fun providesDiskDataSource(dataBase: MovieDBDataBase, mapper: DataMapper): DiskDataSource =
        DiskDataSourceImpl(dataBase, mapper)

    @Provides
    fun provideCloudDataSource(api: MovieDBService, mapper: DataMapper): CloudDataSource =
        CloudDataSourceImpl(api, mapper)

    @Provides
    fun provideNetworkUtils(context: Context): NetworkUtil = NetworkUtil(context)

    @Provides
    fun provideMDBRepository(repository: MDBRepositoryImpl): MDBRepository = repository
}