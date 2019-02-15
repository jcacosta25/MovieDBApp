package io.jcal.movies_provider.repository.api

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.repository.api.model.MoviesDTO
import io.jcal.movies_provider.repository.api.model.TvShowsDTO
import io.jcal.movies_provider.repository.api.network.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDBService {

    @GET("/3/movie/popular")
    fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): LiveData<ApiResponse<MoviesDTO, MoviesDTO>>

    @GET("/3/tv/popular")
    fun getPopularTvShows(
        @Query("language") language: String,
        @Query("page") page: Int
    ): LiveData<ApiResponse<TvShowsDTO, TvShowsDTO>>
}