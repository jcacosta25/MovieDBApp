package io.jcal.movies_provider.repository

import io.jcal.movies_provider.repository.mapper.model.MovieModel
import io.jcal.movies_provider.repository.mapper.model.MoviesModel
import io.jcal.movies_provider.repository.mapper.model.TvShowModel
import io.jcal.movies_provider.repository.mapper.model.TvShowsModel

interface MDBRepository {
	
	suspend fun fetchPopularMovies(language: String, page: Int): MoviesModel
	
	suspend fun loadPopularMovies(): MoviesModel
	
	suspend fun insertAllMovies(model: MoviesModel): List<Long>
	
	suspend fun insertMoviesCoroutines(model: MoviesModel)
	
	suspend fun fetchMovie(movieId: Int): MovieModel
	
	suspend fun loadMovie(movieId: Int): MovieModel
	
	suspend fun insertMovie(model: MovieModel): Long
	
	suspend fun fetchPopularShows(language: String, page: Int): TvShowsModel
	
	suspend fun loadPopularShows(): TvShowsModel
	
	suspend fun insertTvShows(model: TvShowsModel): List<Long>
	
	suspend fun fetchShow(showId: Int): TvShowModel
	
	suspend fun loadShow(showId: Int): TvShowModel
	
	suspend fun insertTvShow(model: TvShowModel): Long
}
