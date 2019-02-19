package io.jcal.movies_provider.repository.datasource

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.repository.db.entity.MovieEntity
import io.jcal.movies_provider.repository.db.entity.TvShowSeasons

interface DiskDataSource {

    fun insertMovies(entity: List<MovieEntity>): List<Long>

    fun insertMovie(entity: MovieEntity): Long

    fun selectMovie(movieId: Int): LiveData<MovieEntity>

    fun selectAllMovies(): LiveData<List<MovieEntity>>

    fun insertTvShows(entity: List<TvShowSeasons>): List<Long>

    fun insertTvShow(entity: TvShowSeasons): Long

    fun selectTvShow(showId: Int): LiveData<TvShowSeasons>

    fun selectAllTvShows(): LiveData<List<TvShowSeasons>>


}