package io.jcal.movies_provider.repository.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations.map
import io.jcal.movies_provider.repository.db.MovieDBDataBase
import io.jcal.movies_provider.repository.db.entity.MovieEntity
import io.jcal.movies_provider.repository.db.entity.TvShowSeasons
import io.jcal.movies_provider.repository.mapper.DataMapper
import io.jcal.movies_provider.repository.mapper.model.MovieModel
import io.jcal.movies_provider.repository.mapper.model.MoviesModel
import io.jcal.movies_provider.repository.mapper.model.TvShowModel
import io.jcal.movies_provider.repository.mapper.model.TvShowsModel
import javax.inject.Inject

class DiskDataSourceImpl @Inject constructor(
    private val dataBase: MovieDBDataBase,
    private val mapper: DataMapper
) :
    DiskDataSource {

    override fun insertMovies(entity: List<MovieEntity>): List<Long> =
        dataBase.movieDao().insertAll(entity)

    override fun insertMovie(entity: MovieEntity): Long = dataBase.movieDao().insert(entity)

    override fun insertTvShows(entity: List<TvShowSeasons>) = dataBase.tvShowDao().insertAll(entity)

    override fun insertTvShow(entity: TvShowSeasons) = dataBase.tvShowDao().insert(entity)

    override fun selectMovie(movieId: Int): LiveData<MovieEntity> =
        dataBase.movieDao().findByMovie(movieId)

    override fun selectAllMovies(): LiveData<List<MovieEntity>> = dataBase.movieDao().getAllMovies()

    override fun selectTvShow(showId: Int): LiveData<TvShowSeasons> =
        dataBase.tvShowDao().findShow(showId)

    override fun selectAllTvShows(): LiveData<List<TvShowSeasons>> =
        dataBase.tvShowDao().getAllShows()

    override fun insertMoviesModel(entity: List<MovieModel>): List<Long> =
        dataBase.movieDao().insertAll(entity.map { mapper.convert(it) })

    override fun insertMovieModel(entity: MovieModel): Long =
        dataBase.movieDao().insert(mapper.convert(entity))

    override fun selectMovieModel(movieId: Int): LiveData<MovieModel> =
        map(dataBase.movieDao().findByMovie(movieId)) {
            mapper.convert(it)
        }

    override fun selectAllMoviesModel(): LiveData<MoviesModel> =
        map(dataBase.movieDao().getAllMovies()) {
            mapper.convert(it)
        }

    override fun insertTvShowsModel(entity: List<TvShowModel>): List<Long> =
        dataBase.tvShowDao().insertAll(mapper.convert(entity))

    override fun insertTvShowModel(entity: TvShowModel): Long =
        dataBase.tvShowDao().insertShow(mapper.convert(entity).tvShowEntity)

    override fun selectTvShowModel(showId: Int): LiveData<TvShowModel> =
        map(dataBase.tvShowDao().findShow(showId)) {
            mapper.convert(it)
        }

    override fun selectAllTvShowsModel(): LiveData<TvShowsModel> =
        map(dataBase.tvShowDao().getAllShows()) {
            mapper.convert(it)
        }

    override suspend fun insertMoviesModelCoroutine(entity: List<MovieModel>) {
        dataBase.movieDao().insertAllCoroutines(entity.map { mapper.convert(it) })
    }

    override suspend fun insertMovieModelCoroutines(entity: MovieModel) {
        dataBase.movieDao().insertCoroutines(mapper.convert(entity))
    }

    override suspend fun selectMovieModelCoroutines(movieId: Int): MovieModel {
        return mapper.convert(dataBase.movieDao().findMovieCoroutines(movieId))
    }

    override suspend fun selectAllMoviesModelCoroutines(): MoviesModel {
        return mapper.convert(dataBase.movieDao().getAllMoviesCoroutines())
    }
}
