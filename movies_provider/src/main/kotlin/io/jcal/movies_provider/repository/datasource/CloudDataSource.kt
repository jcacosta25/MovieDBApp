package io.jcal.movies_provider.repository.datasource

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.repository.api.model.MoviesDTO
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
}