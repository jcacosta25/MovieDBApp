package io.jcal.provider.repository

import io.jcal.provider.repository.datasource.CloudDataSource
import io.jcal.provider.repository.datasource.DiskDataSource
import io.jcal.provider.repository.mapper.model.MovieModel
import io.jcal.provider.repository.mapper.model.MoviesModel
import io.jcal.provider.repository.mapper.model.TvShowModel
import io.jcal.provider.repository.mapper.model.TvShowsModel
import javax.inject.Inject

class MDBRepositoryImpl @Inject constructor(
    private val cloudDataSource: CloudDataSource,
    private val diskDataSource: DiskDataSource
) : MDBRepository {

	override suspend fun fetchPopularMovies(language: String, page: Int): MoviesModel =
		cloudDataSource.fetchPopularMovies(language, page)

	override suspend fun loadPopularMovies(): MoviesModel =
		diskDataSource.selectAllMoviesModelCoroutines()

	override suspend fun insertMoviesCoroutines(model: MoviesModel) {
		diskDataSource.insertMoviesModelCoroutine(model.results)
	}

	override suspend fun fetchMovie(movieId: Int): MovieModel = cloudDataSource.fetchMovie(movieId)

	override suspend fun loadMovie(movieId: Int): MovieModel =
		diskDataSource.selectMovieModel(movieId)

	override suspend fun fetchPopularShows(language: String, page: Int): TvShowsModel =
		cloudDataSource.fetchPopularTvShows(language, page)

	override suspend fun loadPopularShows(): TvShowsModel = diskDataSource.selectAllTvShowsModel()

	override suspend fun fetchShow(showId: Int): TvShowModel =
		cloudDataSource.fetchTvShow(showId)

	override suspend fun loadShow(showId: Int): TvShowModel =
		diskDataSource.selectTvShowModel(showId)

	override suspend fun insertAllMovies(model: MoviesModel): List<Long> =
		diskDataSource.insertMoviesModel(model.results)

	override suspend fun insertMovie(model: MovieModel): Long =
		diskDataSource.insertMovieModel(model)

	override suspend fun insertTvShows(model: TvShowsModel): List<Long> =
		diskDataSource.insertTvShowsModel(model.results)

	override suspend fun insertTvShow(model: TvShowModel): Long =
		diskDataSource.insertTvShowModel(model)
}
