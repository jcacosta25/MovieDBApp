package io.jcal.provider.repository.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import io.jcal.provider.repository.db.DatabaseConstants
import io.jcal.provider.repository.db.entity.MovieEntity

@Dao
interface MovieDao : BaseDao<MovieEntity> {

	@Query("select * from ${DatabaseConstants.TABLE_MOVIE} where ${DatabaseConstants.COLUMN_ID} = :movieId")
	fun findByMovie(movieId: Int): LiveData<MovieEntity>

	@Query("select * from ${DatabaseConstants.TABLE_MOVIE} where ${DatabaseConstants.COLUMN_ID} = :movieId")
	suspend fun findMovieCoroutines(movieId: Int): MovieEntity?

	@Query("select * from ${DatabaseConstants.TABLE_MOVIE}")
	fun getAllMovies(): LiveData<List<MovieEntity>>

	@Query("select * from ${DatabaseConstants.TABLE_MOVIE}")
	suspend fun getAllMoviesCoroutines(): List<MovieEntity>
}
