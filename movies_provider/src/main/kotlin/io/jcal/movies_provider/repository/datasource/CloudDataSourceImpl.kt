package io.jcal.movies_provider.repository.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import io.jcal.movies_provider.repository.api.MovieDBService
import io.jcal.movies_provider.repository.api.model.MovieDTO
import io.jcal.movies_provider.repository.api.model.MoviesDTO
import io.jcal.movies_provider.repository.api.model.TvShowDTO
import io.jcal.movies_provider.repository.api.model.TvShowsDTO
import io.jcal.movies_provider.repository.api.network.ApiResponse
import io.jcal.movies_provider.repository.api.network.HttpErrorCodes
import io.jcal.movies_provider.repository.mapper.DataMapper
import io.jcal.movies_provider.repository.mapper.model.MovieModel
import io.jcal.movies_provider.repository.mapper.model.MoviesModel
import io.jcal.movies_provider.repository.mapper.model.TvShowModel
import io.jcal.movies_provider.repository.mapper.model.TvShowsModel
import java.io.IOException
import javax.inject.Inject

class CloudDataSourceImpl @Inject constructor(
    private val api: MovieDBService,
    private val mapper: DataMapper
) : CloudDataSource {
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

    override suspend fun fetchPopularMovies(language: String, page: Int): LiveData<MoviesModel> {
        val liveDataPopularMovies = MediatorLiveData<MoviesModel>()
        val moviesModel = try {
            val response = api.fetchPopularMovies(language, page).await()
            if (response.isSuccessful && response.body != null) {
                mapper.convert(response.body)
            } else {
                mapper.createDomainModel(
                    response.code,
                    MoviesModel::class.java
                )
            }

        } catch (e: IOException) {
            mapper.createDomainModel(
                HttpErrorCodes.NO_NETWORK_RESPONSE_CODE,
                MoviesModel::class.java
            )
        }
        liveDataPopularMovies.postValue(moviesModel)
        return liveDataPopularMovies
    }

    override suspend fun fetchPopularTvShows(language: String, page: Int): LiveData<TvShowsModel> {
        val liveDataPopularShows = MediatorLiveData<TvShowsModel>()
        val showsModel = try {
            val response = api.fetchPopularTvShows(language, page).await()
            if (response.isSuccessful && response.body != null) {
                mapper.convert(response.body)
            } else {
                mapper.createDomainModel(
                    response.code,
                    TvShowsModel::class.java
                )
            }

        } catch (e: IOException) {
            mapper.createDomainModel(
                HttpErrorCodes.NO_NETWORK_RESPONSE_CODE,
                TvShowsModel::class.java
            )
        }
        liveDataPopularShows.postValue(showsModel)
        return liveDataPopularShows
    }

    override suspend fun fetchMovie(movieId: Int, language: String): LiveData<MovieModel> {
        val liveDataMovie = MediatorLiveData<MovieModel>()
        val movieModel = try {
            val response = api.fetchMovie(movieId, language).await()
            if (response.isSuccessful && response.body != null) {
                mapper.convert(response.body)
            } else {
                mapper.createDomainModel(
                    response.code,
                    MovieModel::class.java
                )
            }

        } catch (e: IOException) {
            mapper.createDomainModel(
                HttpErrorCodes.NO_NETWORK_RESPONSE_CODE,
                MovieModel::class.java
            )
        }
        liveDataMovie.postValue(movieModel)
        return liveDataMovie
    }

    override suspend fun fetchTvShow(tvShowId: Int, language: String): LiveData<TvShowModel> {
        val liveDataShow = MediatorLiveData<TvShowModel>()
        val showModel = try {
            val response = api.fetchTvShow(tvShowId, language).await()
            if (response.isSuccessful && response.body != null) {
                mapper.convert(response.body)
            } else {
                mapper.createDomainModel(
                    response.code,
                    TvShowModel::class.java
                )
            }
        } catch (e: IOException) {
            mapper.createDomainModel(
                HttpErrorCodes.NO_NETWORK_RESPONSE_CODE,
                TvShowModel::class.java
            )
        }
        liveDataShow.postValue(showModel)
        return liveDataShow
    }
}

