package io.jcal.provider.repository.api

import androidx.lifecycle.LiveData
import io.jcal.provider.repository.api.model.MovieDTO
import io.jcal.provider.repository.api.model.MoviesDTO
import io.jcal.provider.repository.api.model.ServerErrorDto
import io.jcal.provider.repository.api.model.TvShowDTO
import io.jcal.provider.repository.api.model.TvShowsDTO
import io.jcal.provider.repository.api.network.coroutinesAdapter.NetworkResponse
import io.jcal.provider.repository.api.network.liveDataAdapter.ApiResponse
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
	suspend fun fetchPopularMovies(
	    @Query("language") language: String,
	    @Query("page") page: Int
	): NetworkResponse<MoviesDTO, ServerErrorDto>
	
	@GET("/3/tv/popular")
	fun getPopularTvShows(
	    @Query("language") language: String,
	    @Query("page") page: Int
	): LiveData<ApiResponse<TvShowsDTO, TvShowsDTO>>
	
	@GET("/3/tv/popular")
	suspend fun fetchPopularTvShows(
	    @Query("language") language: String,
	    @Query("page") page: Int
	): NetworkResponse<TvShowsDTO, ServerErrorDto>
	
	@GET("/3/movie/{movie_id}")
	fun getMovie(
	    @Path("movie_id") movieId: Int,
	    @Query("language") language: String
	): LiveData<ApiResponse<MovieDTO, MovieDTO>>
	
	@GET("/3/movie/{movie_id}")
	suspend fun fetchMovie(
	    @Path("movie_id") movieId: Int,
	    @Query("language") language: String
	): NetworkResponse<MovieDTO, ServerErrorDto>
	
	@GET("/3/tv/{show_id}")
	fun getTvShow(
	    @Path("show_id") showId: Int,
	    @Query("language") language: String
	): LiveData<ApiResponse<TvShowDTO, TvShowDTO>>
	
	@GET("/3/tv/{show_id}")
	suspend fun fetchTvShow(
	    @Path("show_id") showId: Int,
	    @Query("language") language: String
	): NetworkResponse<TvShowDTO, ServerErrorDto>
}
