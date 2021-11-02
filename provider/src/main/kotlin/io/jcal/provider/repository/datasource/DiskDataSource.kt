package io.jcal.provider.repository.datasource

import io.jcal.provider.repository.db.entity.MovieEntity
import io.jcal.provider.repository.db.entity.TvShowSeasons
import io.jcal.provider.repository.mapper.model.MovieModel
import io.jcal.provider.repository.mapper.model.MoviesModel
import io.jcal.provider.repository.mapper.model.TvShowModel
import io.jcal.provider.repository.mapper.model.TvShowsModel

interface DiskDataSource {

	suspend fun insertMovies(entity: List<MovieEntity>): List<Long>

	suspend fun insertMovie(entity: MovieEntity): Long

	suspend fun insertTvShows(entity: List<TvShowSeasons>): List<Long>

	suspend fun insertTvShow(entity: TvShowSeasons): Long

	suspend fun insertMoviesModel(entity: List<MovieModel>): List<Long>

	suspend fun insertMoviesModelCoroutine(entity: List<MovieModel>)

	suspend fun insertMovieModel(entity: MovieModel): Long

	suspend fun insertMovieModelCoroutines(entity: MovieModel)

	suspend fun selectMovieModel(movieId: Int): MovieModel

	suspend fun selectAllMoviesModelCoroutines(): MoviesModel

	suspend fun insertTvShowsModel(entity: List<TvShowModel>): List<Long>

	suspend fun insertTvShowModel(entity: TvShowModel): Long

	suspend fun selectTvShowModel(showId: Int): TvShowModel

	suspend fun selectAllTvShowsModel(): TvShowsModel
}
