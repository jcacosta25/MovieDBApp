package io.jcal.movies_provider.repository.api

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.repository.api.model.FlicksListResponse
import io.jcal.movies_provider.repository.api.network.ApiResponse
import io.jcal.movies_provider.repository.api.network.HttpBaseValues
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDBService {

    @GET("/movie/popular")
    fun getPopularMovies(
        @Query("language") language: String = HttpBaseValues.LANGUAGE,
        @Query("page") page: Int = HttpBaseValues.PAGE
    ): LiveData<ApiResponse<FlicksListResponse, FlicksListResponse>>

    @GET("/tv/popular")
    fun getPopularTvShows(
        @Query("language") language: String = HttpBaseValues.LANGUAGE,
        @Query("page") page: Int = HttpBaseValues.PAGE
    ): LiveData<ApiResponse<FlicksListResponse, FlicksListResponse>>
}