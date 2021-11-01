package io.jcal.movies_provider.repository.datasource

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.domain.interactor.base.Status
import io.jcal.movies_provider.repository.api.MovieDBService
import io.jcal.movies_provider.repository.api.model.MovieDTO
import io.jcal.movies_provider.repository.api.model.MoviesDTO
import io.jcal.movies_provider.repository.api.model.TvShowDTO
import io.jcal.movies_provider.repository.api.model.TvShowsDTO
import io.jcal.movies_provider.repository.api.network.liveDataAdapter.ApiResponse
import io.jcal.movies_provider.repository.mapper.DataMapper
import io.jcal.movies_provider.repository.mapper.model.MovieModel
import io.jcal.movies_provider.repository.mapper.model.MoviesModel
import io.jcal.movies_provider.repository.mapper.model.TvShowModel
import io.jcal.movies_provider.repository.mapper.model.TvShowsModel
import javax.inject.Inject

class CloudDataSourceImpl @Inject constructor(
    private val api: MovieDBService,
    private val mapper: DataMapper
) : CloudDataSource, BaseCloudDataSource() {
	override fun getPopularMovies(
	    language: String,
	    page: Int
	): LiveData<ApiResponse<MoviesDTO, MoviesDTO>> =
		api.getPopularMovies(language, page)

	override fun getPopularTvShows(
	    language: String,
	    page: Int
	): LiveData<ApiResponse<TvShowsDTO, TvShowsDTO>> =
		api.getPopularTvShows(language, page)

	override fun getMovie(
	    movieId: Int,
	    language: String
	): LiveData<ApiResponse<MovieDTO, MovieDTO>> = api.getMovie(movieId, language)

	override fun getTvShow(
	    tvShowId: Int,
	    language: String
	): LiveData<ApiResponse<TvShowDTO, TvShowDTO>> = api.getTvShow(tvShowId, language)

	override suspend fun fetchPopularMovies(language: String, page: Int): MoviesModel {
		val result = getResultCoroutines { api.fetchPopularMovies(language, page) }
		return when (result.status) {
			Status.SUCCESS -> mapper.convert(result.body)
			else -> mapper.createDomainModel(
				errorCode = result.errorBody?.statusCode ?: result.errorCode,
				clazz = MoviesModel::class.java
			)
		}
	}

	override suspend fun fetchPopularTvShows(language: String, page: Int): TvShowsModel {
		val result = getResultCoroutines { api.fetchPopularTvShows(language, page) }
		return when (result.status) {
			Status.SUCCESS -> mapper.convert(result.body)
			else -> mapper.createDomainModel(
				errorCode = result.errorBody?.statusCode ?: result.errorCode,
				clazz = TvShowsModel::class.java
			)
		}
	}

	override suspend fun fetchMovie(movieId: Int, language: String): MovieModel {
		val result = getResultCoroutines { api.fetchMovie(movieId, language) }
		return when (result.status) {
			Status.SUCCESS -> mapper.convert(result.body)
			else -> mapper.createDomainModel(
				errorCode = result.errorBody?.statusCode ?: result.errorCode,
				clazz = MovieModel::class.java
			)
		}
	}

	override suspend fun fetchTvShow(tvShowId: Int, language: String): TvShowModel {
		val result = getResultCoroutines { api.fetchTvShow(tvShowId, language) }
		return when (result.status) {
			Status.SUCCESS -> mapper.convert(result.body)
			else -> mapper.createDomainModel(
				errorCode = result.errorBody?.statusCode ?: result.errorCode,
				clazz = TvShowModel::class.java
			)
		}
	}
}
