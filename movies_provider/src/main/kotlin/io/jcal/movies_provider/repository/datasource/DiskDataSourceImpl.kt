package io.jcal.movies_provider.repository.datasource

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

    override suspend fun insertMovies(entity: List<MovieEntity>): List<Long> =
        dataBase.movieDao().insertAll(entity)

    override suspend fun insertMovie(entity: MovieEntity): Long = dataBase.movieDao().insert(entity)

    override suspend fun insertTvShows(entity: List<TvShowSeasons>) =
        dataBase.tvShowDao().insertAll(entity)

    override suspend fun insertTvShow(entity: TvShowSeasons) = dataBase.tvShowDao().insert(entity)

    override suspend fun insertMoviesModel(entity: List<MovieModel>): List<Long> =
        dataBase.movieDao().insertAll(entity.map { mapper.convert(it) })

    override suspend fun insertMovieModel(entity: MovieModel): Long =
        dataBase.movieDao().insert(mapper.convert(entity))

    override suspend fun selectMovieModel(movieId: Int): MovieModel =
        mapper.convert(dataBase.movieDao().findMovieCoroutines(movieId))

    override suspend fun insertTvShowsModel(entity: List<TvShowModel>): List<Long> =
        dataBase.tvShowDao().insertAll(mapper.convert(entity))

    override suspend fun insertTvShowModel(entity: TvShowModel): Long =
        dataBase.tvShowDao().insertShow(mapper.convert(entity).tvShowEntity)

    override suspend fun selectTvShowModel(showId: Int): TvShowModel =
        mapper.convert(dataBase.tvShowDao().findShow(showId))

    override suspend fun selectAllTvShowsModel(): TvShowsModel =
        mapper.convert(dataBase.tvShowDao().getAllShows())

    override suspend fun insertMoviesModelCoroutine(entity: List<MovieModel>) {
        dataBase.movieDao().insertAllCoroutines(entity.map { mapper.convert(it) })
    }

    override suspend fun insertMovieModelCoroutines(entity: MovieModel) {
        dataBase.movieDao().insertCoroutines(mapper.convert(entity))
    }

    override suspend fun selectAllMoviesModelCoroutines(): MoviesModel {
        return mapper.convert(dataBase.movieDao().getAllMoviesCoroutines())
    }
}
