package io.jcal.theMovie.di.module.core

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import io.jcal.movies_provider.repository.db.DatabaseConstants.DATABASE_NAME
import io.jcal.movies_provider.repository.db.DatabaseConstants.DATABASE_NAME_PROPERTY
import javax.inject.Named
import javax.inject.Singleton


@Module
class ApplicationModule {
    @Provides
    @Singleton
    fun providesApplicationContext(application: Application): Context =
        application.applicationContext

    @Provides
    @Singleton
    fun providesResources(application: Application): Resources = application.resources

    @Provides
    @Named(DATABASE_NAME_PROPERTY)
    fun provideDatabaseName(): String = DATABASE_NAME
}