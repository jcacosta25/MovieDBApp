package io.jcal.movies_provider.repository.api

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.repository.api.model.MovieDTO
import io.jcal.movies_provider.repository.api.model.MoviesDTO
import io.jcal.movies_provider.repository.api.model.TvShowDTO
import io.jcal.movies_provider.repository.api.model.TvShowsDTO
import io.jcal.movies_provider.repository.api.network.ApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBService {

    @GET("/3/movie/popular")
    fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): LiveData<ApiResponse<MoviesDTO, MoviesDTO>>

    @GET("/3/movie/popular")
    fun fetchPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Deferred<ApiResponse<MoviesDTO, MoviesDTO>>

    @GET("/3/tv/popular")
    fun getPopularTvShows(
        @Query("language") language: String,
        @Query("page") page: Int
    ): LiveData<ApiResponse<TvShowsDTO, TvShowsDTO>>

    @GET("/3/tv/popular")
    fun fetchPopularTvShows(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Deferred<ApiResponse<TvShowsDTO, TvShowsDTO>>

    @GET("/3/movie/{movie_id}")
    fun getMovie(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): LiveData<ApiResponse<MovieDTO, MovieDTO>>

    @GET("/3/movie/{movie_id}")
    fun fetchMovie(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): Deferred<ApiResponse<MovieDTO, MovieDTO>>

    @GET("/3/tv/{show_id}")
    fun getTvShow(
        @Path("show_id") showId: Int,
        @Query("language") language: String
    ): LiveData<ApiResponse<TvShowDTO, TvShowDTO>>

    @GET("/3/tv/{show_id}")
    fun fetchTvShow(
        @Path("show_id") showId: Int,
        @Query("language") language: String
    ): Deferred<ApiResponse<TvShowDTO, TvShowDTO>>
}