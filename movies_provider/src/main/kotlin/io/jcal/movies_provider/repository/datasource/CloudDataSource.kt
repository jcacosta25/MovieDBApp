package io.jcal.movies_provider.repository.datasource

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.repository.api.model.MovieDTO
import io.jcal.movies_provider.repository.api.model.MoviesDTO
import io.jcal.movies_provider.repository.api.model.TvShowDTO
import io.jcal.movies_provider.repository.api.model.TvShowsDTO
import io.jcal.movies_provider.repository.api.network.ApiResponse
import io.jcal.movies_provider.repository.api.network.HttpBaseValues

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
    ):LiveData<ApiResponse<MovieDTO, MovieDTO>>

    fun getTvShow(
        tvShowId: Int = 0,
        language: String = HttpBaseValues.LANGUAGE
    ):LiveData<ApiResponse<TvShowDTO, TvShowDTO>>
}