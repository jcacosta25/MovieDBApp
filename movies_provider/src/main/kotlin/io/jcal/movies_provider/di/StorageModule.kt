package io.jcal.movies_provider.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.jcal.movies_provider.repository.db.DatabaseConstants.DATABASE_NAME
import io.jcal.movies_provider.repository.db.DatabaseConstants.DATABASE_NAME_PROPERTY
import io.jcal.movies_provider.repository.db.MovieDBDataBase
import javax.inject.Named

@Module
class StorageModule {

    @Provides
    fun providesDatabase(
        context: Context,
        @Named(DATABASE_NAME_PROPERTY) databaseName: String = DATABASE_NAME
    ): MovieDBDataBase =
        Room.databaseBuilder(context, MovieDBDataBase::class.java, databaseName).build()
}
