package io.jcal.provider.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.jcal.provider.repository.db.DatabaseConstants.DATABASE_NAME
import io.jcal.provider.repository.db.MovieDBDataBase
import javax.inject.Qualifier

@Retention
@Qualifier
annotation class DatabaseName

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {
	
	@Provides
	fun providesDatabase(
	    context: Context,
	    @DatabaseName databaseName: String = DATABASE_NAME
	): MovieDBDataBase =
		Room.databaseBuilder(context, MovieDBDataBase::class.java, databaseName).build()
}
