package io.jcal.movies_provider.repository.datasource

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.repository.db.MovieDBDataBase
import io.jcal.movies_provider.repository.db.entity.MovieEntity
import io.jcal.movies_provider.repository.db.entity.TvShowSeasons
import javax.inject.Inject

class DiskDataSourceImpl @Inject constructor(val dataBase: MovieDBDataBase) : DiskDataSource {

    override fun insertMovies(entity: List<MovieEntity>): List<Long> =
        dataBase.movieDao().insertAll(entity)

    override fun insertMovie(entity: MovieEntity): Long = dataBase.movieDao().insert(entity)

    override fun insertTvShows(entity: List<TvShowSeasons>) = dataBase.tvShowDao().insertAll(entity)

    override fun insertTvShow(entity: TvShowSeasons) = dataBase.tvShowDao().insert(entity)

    override fun selectMovie(movieTitle: String): LiveData<MovieEntity> =
        dataBase.movieDao().findByMovieTitle(movieTitle)

    override fun selectAllMovies(): LiveData<List<MovieEntity>> = dataBase.movieDao().getAllMovies()

    override fun selectTvShow(tvShowTitle: String): LiveData<TvShowSeasons> =
        dataBase.tvShowDao().findShowByTitle(tvShowTitle)

    override fun selectAllTvShows(): LiveData<List<TvShowSeasons>> =
        dataBase.tvShowDao().getAllShows()
}