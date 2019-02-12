package io.jcal.movies_provider.repository.datasource

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.repository.db.entity.MovieEntity
import io.jcal.movies_provider.repository.db.entity.TvShowEntity

interface DiskDataSource {

    fun insertMovies(entity: List<MovieEntity>): List<Long>

    fun insertMovie(entity: MovieEntity): Long

    fun insertTvShows(entity: List<TvShowEntity>): List<Long>

    fun insertTvShow(entity: TvShowEntity): Long

    fun selectMovie(movieTitle: String): LiveData<MovieEntity>

    fun selectAllMovies(): LiveData<List<MovieEntity>>

    fun selectTvShow(tvShowTitle: String): LiveData<TvShowEntity>

    fun selectAllTvShows(): LiveData<List<TvShowEntity>>
}