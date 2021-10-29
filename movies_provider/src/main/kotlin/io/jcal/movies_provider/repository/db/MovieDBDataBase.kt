package io.jcal.movies_provider.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.jcal.movies_provider.repository.db.converter.Converter
import io.jcal.movies_provider.repository.db.dao.MovieDao
import io.jcal.movies_provider.repository.db.dao.TvShowDao
import io.jcal.movies_provider.repository.db.entity.EpisodeEntity
import io.jcal.movies_provider.repository.db.entity.MovieEntity
import io.jcal.movies_provider.repository.db.entity.SeasonEntity
import io.jcal.movies_provider.repository.db.entity.TvShowEntity

@Database(
	entities = [MovieEntity::class, TvShowEntity::class, SeasonEntity::class, EpisodeEntity::class],
	version = DatabaseConstants.DATABASE_VERSION
)
@TypeConverters(Converter::class)
abstract class MovieDBDataBase : RoomDatabase() {

	abstract fun movieDao(): MovieDao

	abstract fun tvShowDao(): TvShowDao
}
