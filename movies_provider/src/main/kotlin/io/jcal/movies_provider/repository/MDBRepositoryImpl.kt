package io.jcal.movies_provider.repository

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.repository.datasource.CloudDataSource
import io.jcal.movies_provider.repository.datasource.DiskDataSource
import io.jcal.movies_provider.repository.mapper.model.MovieModel
import io.jcal.movies_provider.repository.mapper.model.MoviesModel
import io.jcal.movies_provider.repository.mapper.model.TvShowModel
import io.jcal.movies_provider.repository.mapper.model.TvShowsModel
import javax.inject.Inject

class MDBRepositoryImpl @Inject constructor(
    private val cloudDataSource: CloudDataSource,
    private val diskDataSource: DiskDataSource
) : MDBRepository {

    override suspend fun getPopularMovies(): MoviesModel = cloudDataSource.fetchPopularMovies()

    override fun loadPopularMovies(): LiveData<MoviesModel> = diskDataSource.selectAllMoviesModel()

    override suspend fun getMovie(movieId: Int): MovieModel = cloudDataSource.fetchMovie(movieId)


    override fun loadMovie(movieId: Int): LiveData<MovieModel> =
        diskDataSource.selectMovieModel(movieId)

    override suspend fun getPopularShows(): TvShowsModel = cloudDataSource.fetchPopularTvShows()

    override fun loadPopularShows(): LiveData<TvShowsModel> = diskDataSource.selectAllTvShowsModel()

    override suspend fun getShow(showId: Int): TvShowModel =
        cloudDataSource.fetchTvShow(showId)

    override fun loadShow(showId: Int): LiveData<TvShowModel> =
        diskDataSource.selectTvShowModel(showId)

    override fun insertAllMovies(model: MoviesModel): List<Long> =
        diskDataSource.insertMoviesModel(model.results)

    override fun insertMovie(model: MovieModel): Long = diskDataSource.insertMovieModel(model)

    override fun insertTvShows(model: TvShowsModel): List<Long> =
        diskDataSource.insertTvShowsModel(model.results)

    override fun insertTvShow(model: TvShowModel): Long = diskDataSource.insertTvShowModel(model)
}