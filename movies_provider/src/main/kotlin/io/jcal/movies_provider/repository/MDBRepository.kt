package io.jcal.movies_provider.repository

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.repository.mapper.model.MovieModel
import io.jcal.movies_provider.repository.mapper.model.MoviesModel
import io.jcal.movies_provider.repository.mapper.model.TvShowModel
import io.jcal.movies_provider.repository.mapper.model.TvShowsModel

interface MDBRepository {

    suspend fun getPopularMovies(language:String,page:Int): MoviesModel

    fun loadPopularMovies(): LiveData<MoviesModel>

    fun insertAllMovies(model: MoviesModel): List<Long>

    suspend fun getMovie(movieId: Int): MovieModel

    fun loadMovie(movieId: Int): LiveData<MovieModel>

    fun insertMovie(model: MovieModel): Long

    suspend fun getPopularShows(): TvShowsModel

    fun loadPopularShows(): LiveData<TvShowsModel>

    fun insertTvShows(model: TvShowsModel): List<Long>

    suspend fun getShow(showId: Int): TvShowModel

    fun loadShow(showId: Int): LiveData<TvShowModel>

    fun insertTvShow(model: TvShowModel): Long

}