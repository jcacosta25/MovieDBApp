package io.jcal.movies_provider.repository

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.repository.mapper.model.MovieModel
import io.jcal.movies_provider.repository.mapper.model.MoviesModel
import io.jcal.movies_provider.repository.mapper.model.TvShowModel
import io.jcal.movies_provider.repository.mapper.model.TvShowsModel

interface MDBRepository {

    suspend fun getPopularMovies(): LiveData<out MoviesModel>

    suspend fun getMovie(movieId: Int): LiveData<out MovieModel>

    suspend fun getPopularShows(): LiveData<out TvShowsModel>

    suspend fun getShow(showId: Int): LiveData<out TvShowModel>

}