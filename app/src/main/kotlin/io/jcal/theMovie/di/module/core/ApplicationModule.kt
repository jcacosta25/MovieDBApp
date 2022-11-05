package io.jcal.theMovie.di.module.core

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.jcal.provider.di.ApiToken
import io.jcal.provider.di.DatabaseName
import io.jcal.provider.repository.db.DatabaseConstants.DATABASE_NAME

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
	@Provides
	fun providesApplicationContext(application: Application): Context =
		application.applicationContext
	
	@Provides
	fun providesResources(application: Application): Resources = application.resources
	
	@DatabaseName
	@Provides
	fun provideDatabaseName(): String = DATABASE_NAME
	
	@Provides
	@ApiToken
	fun provideMovieDBApiToken(): String = "aa94b3c2c71ca34288378f22d536ab1f"
}
