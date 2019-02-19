package io.jcal.movies_provider.repository

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.repository.mapper.model.MovieModel
import io.jcal.movies_provider.repository.mapper.model.MoviesModel
import io.jcal.movies_provider.repository.mapper.model.TvShowModel
import io.jcal.movies_provider.repository.mapper.model.TvShowsModel

interface Repository {
    fun fetchPopularMovies(): LiveData<MoviesModel>

    fun fetchMovie(movieId: Int): LiveData<MovieModel>

    fun loadMovie(movieId: Int): LiveData<MovieModel>

    fun loadAllMovies(): LiveData<MoviesModel>

    fun insertMovies(movies: List<MovieModel>): List<Long>

    fun insertMovie(movie: MovieModel): Long

    fun fetchPopularTvShows(): LiveData<TvShowsModel>

    fun loadAllTvShows(): LiveData<TvShowsModel>

    fun fetchTvShow(showId: Int): LiveData<TvShowModel>

    fun loadTvShow(showId: Int): LiveData<TvShowModel>

    fun insertTvShows(tvShows: List<TvShowModel>): List<Long>

    fun insertTvShow(tvShow: TvShowModel): Long
}