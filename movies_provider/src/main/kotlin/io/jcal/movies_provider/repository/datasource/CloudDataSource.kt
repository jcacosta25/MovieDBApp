package io.jcal.movies_provider.repository.datasource

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.repository.api.model.MovieDTO
import io.jcal.movies_provider.repository.api.model.MoviesDTO
import io.jcal.movies_provider.repository.api.model.TvShowDTO
import io.jcal.movies_provider.repository.api.model.TvShowsDTO
import io.jcal.movies_provider.repository.api.network.HttpBaseValues
import io.jcal.movies_provider.repository.api.network.liveDataAdapter.ApiResponse
import io.jcal.movies_provider.repository.mapper.model.MovieModel
import io.jcal.movies_provider.repository.mapper.model.MoviesModel
import io.jcal.movies_provider.repository.mapper.model.TvShowModel
import io.jcal.movies_provider.repository.mapper.model.TvShowsModel

interface CloudDataSource {

	fun getPopularMovies(
	    language: String = HttpBaseValues.LANGUAGE,
	    page: Int = HttpBaseValues.PAGE
	): LiveData<ApiResponse<MoviesDTO, MoviesDTO>>

	fun getPopularTvShows(
	    language: String = HttpBaseValues.LANGUAGE,
	    page: Int = HttpBaseValues.PAGE
	): LiveData<ApiResponse<TvShowsDTO, TvShowsDTO>>

	fun getMovie(
	    movieId: Int = 0,
	    language: String = HttpBaseValues.LANGUAGE
	): LiveData<ApiResponse<MovieDTO, MovieDTO>>

	fun getTvShow(
	    tvShowId: Int = 0,
	    language: String = HttpBaseValues.LANGUAGE
	): LiveData<ApiResponse<TvShowDTO, TvShowDTO>>

	suspend fun fetchPopularMovies(
	    language: String = HttpBaseValues.LANGUAGE,
	    page: Int = HttpBaseValues.PAGE
	): MoviesModel

	suspend fun fetchPopularTvShows(
	    language: String = HttpBaseValues.LANGUAGE,
	    page: Int = HttpBaseValues.PAGE
	): TvShowsModel

	suspend fun fetchMovie(
	    movieId: Int = 0,
	    language: String = HttpBaseValues.LANGUAGE
	): MovieModel

	suspend fun fetchTvShow(
	    tvShowId: Int = 0,
	    language: String = HttpBaseValues.LANGUAGE
	): TvShowModel
}
