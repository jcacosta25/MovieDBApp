package io.jcal.theMovie.di.module.core

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import io.jcal.movies_provider.di.NetworkModule.Companion.API_TOKEN_PROPERTY
import io.jcal.movies_provider.repository.db.DatabaseConstants.DATABASE_NAME
import io.jcal.movies_provider.repository.db.DatabaseConstants.DATABASE_NAME_PROPERTY
import javax.inject.Named

@Module
class ApplicationModule {
    @Provides
    fun providesApplicationContext(application: Application): Context =
        application.applicationContext

    @Provides
    fun providesResources(application: Application): Resources = application.resources

    @Provides
    @Named(DATABASE_NAME_PROPERTY)
    fun provideDatabaseName(): String = DATABASE_NAME

    @Provides
    @Named(API_TOKEN_PROPERTY)
    fun provideMovieDBApiToken(): String = "aa94b3c2c71ca34288378f22d536ab1f"
}
