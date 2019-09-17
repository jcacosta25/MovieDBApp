package io.jcal.movies_provider.repository.datasource

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.repository.db.entity.MovieEntity
import io.jcal.movies_provider.repository.db.entity.TvShowSeasons
import io.jcal.movies_provider.repository.mapper.model.MovieModel
import io.jcal.movies_provider.repository.mapper.model.MoviesModel
import io.jcal.movies_provider.repository.mapper.model.TvShowModel
import io.jcal.movies_provider.repository.mapper.model.TvShowsModel

interface DiskDataSource {

    fun insertMovies(entity: List<MovieEntity>): List<Long>

    fun insertMovie(entity: MovieEntity): Long

    fun selectMovie(movieId: Int): LiveData<MovieEntity>

    fun selectAllMovies(): LiveData<List<MovieEntity>>

    fun insertTvShows(entity: List<TvShowSeasons>): List<Long>

    fun insertTvShow(entity: TvShowSeasons): Long

    fun selectTvShow(showId: Int): LiveData<TvShowSeasons>

    fun selectAllTvShows(): LiveData<List<TvShowSeasons>>

    fun insertMoviesModel(entity: List<MovieModel>): List<Long>

    fun insertMovieModel(entity: MovieModel): Long

    fun selectMovieModel(movieId: Int): LiveData<MovieModel>

    fun selectAllMoviesModel(): LiveData<MoviesModel>

    fun insertTvShowsModel(entity: List<TvShowModel>): List<Long>

    fun insertTvShowModel(entity: TvShowModel): Long

    fun selectTvShowModel(showId: Int): LiveData<TvShowModel>

    fun selectAllTvShowsModel(): LiveData<TvShowsModel>
}