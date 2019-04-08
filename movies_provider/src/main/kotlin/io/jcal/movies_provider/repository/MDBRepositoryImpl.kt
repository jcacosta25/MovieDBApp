package io.jcal.movies_provider.repository

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.repository.datasource.CloudDataSource
import io.jcal.movies_provider.repository.datasource.DiskDataSource
import io.jcal.movies_provider.repository.mapper.model.MovieModel
import io.jcal.movies_provider.repository.mapper.model.MoviesModel
import io.jcal.movies_provider.repository.mapper.model.TvShowModel
import io.jcal.movies_provider.repository.mapper.model.TvShowsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MDBRepositoryImpl @Inject constructor(
    private val cloudDataSource: CloudDataSource,
    private val diskDataSource: DiskDataSource
) : MDBRepository {

    override suspend fun getPopularMovies(): LiveData<out MoviesModel> =
        withContext(Dispatchers.IO) {
            cloudDataSource.fetchPopularMovies()
        }

    override suspend fun getMovie(movieId: Int): LiveData<out MovieModel> =
        withContext(Dispatchers.IO) {
            cloudDataSource.fetchMovie(movieId)
        }

    override suspend fun getPopularShows(): LiveData<out TvShowsModel> =
        withContext(Dispatchers.IO) {
            cloudDataSource.fetchPopularTvShows()
        }

    override suspend fun getShow(showId: Int): LiveData<out TvShowModel> =
        withContext(Dispatchers.IO) {
            cloudDataSource.fetchTvShow(showId)
        }

}