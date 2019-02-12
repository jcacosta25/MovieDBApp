package io.jcal.movies_provider.repository.datasource

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.repository.api.MovieDBService
import io.jcal.movies_provider.repository.api.model.MoviesDTO
import io.jcal.movies_provider.repository.api.model.TvShowsDTO
import io.jcal.movies_provider.repository.api.network.ApiResponse
import javax.inject.Inject

class CloudDataSourceImpl @Inject constructor(val api: MovieDBService) : CloudDataSource {
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
}