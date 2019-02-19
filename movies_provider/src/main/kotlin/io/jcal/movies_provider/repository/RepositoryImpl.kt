package io.jcal.movies_provider.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import io.jcal.movies_provider.repository.datasource.CloudDataSource
import io.jcal.movies_provider.repository.datasource.DiskDataSource
import io.jcal.movies_provider.repository.mapper.DataMapper
import io.jcal.movies_provider.repository.mapper.model.MovieModel
import io.jcal.movies_provider.repository.mapper.model.MoviesModel
import io.jcal.movies_provider.repository.mapper.model.TvShowModel
import io.jcal.movies_provider.repository.mapper.model.TvShowsModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val cloudDataSource: CloudDataSource,
    private val diskDataSource: DiskDataSource,
    private val dataMapper: DataMapper
) : Repository {

    override fun fetchPopularMovies(): LiveData<MoviesModel> {
        val responseMediatorLiveData = MediatorLiveData<MoviesModel>()
        responseMediatorLiveData.addSource(cloudDataSource.getPopularMovies()) { response ->
            if (response != null && response.isSuccessful && response.body != null) {
                responseMediatorLiveData.postValue(dataMapper.convert(response.body))
            } else {
                responseMediatorLiveData.postValue(
                    dataMapper.createDomainModel(
                        response.code,
                        MoviesModel::class.java
                    )
                )
            }
        }
        return responseMediatorLiveData
    }

    override fun loadMovie(movieId: Int): LiveData<MovieModel> = Transformations.map(
        diskDataSource.selectMovie(movieId)
    ) {
        dataMapper.convert(it)
    }

    override fun fetchMovie(movieId: Int): LiveData<MovieModel> {
        val responseMediatorLiveData = MediatorLiveData<MovieModel>()
        responseMediatorLiveData.addSource(cloudDataSource.getMovie(movieId)) { response ->
            if (response != null && response.isSuccessful && response.body != null) {
                responseMediatorLiveData.postValue(dataMapper.convert(response.body))
            } else {
                responseMediatorLiveData.postValue(
                    dataMapper.createDomainModel(
                        response.code,
                        MovieModel::class.java
                    )
                )
            }
        }
        return responseMediatorLiveData
    }

    override fun loadAllMovies(): LiveData<MoviesModel> =
        Transformations.map(
            diskDataSource.selectAllMovies()
        ) { movies ->
            dataMapper.convert(movies)
        }

    override fun insertMovies(movies: List<MovieModel>): List<Long> =
        diskDataSource.insertMovies(movies.map { dataMapper.convert(it) })

    override fun insertMovie(movie: MovieModel): Long =
        diskDataSource.insertMovie(dataMapper.convert(movie))

    override fun fetchPopularTvShows(): LiveData<TvShowsModel> {
        val responseMediatorLiveData = MediatorLiveData<TvShowsModel>()
        responseMediatorLiveData.addSource(cloudDataSource.getPopularTvShows()) { response ->
            if (response != null && response.isSuccessful && response.body != null) {
                responseMediatorLiveData.postValue(dataMapper.convert(response.body))
            } else {
                responseMediatorLiveData.postValue(
                    dataMapper.createDomainModel(
                        response.code,
                        TvShowsModel::class.java
                    )
                )
            }
        }
        return responseMediatorLiveData
    }

    override fun loadAllTvShows(): LiveData<TvShowsModel> =
        Transformations.map(
            diskDataSource.selectAllTvShows()
        ) { tvShows ->
            dataMapper.convert(tvShows)
        }

    override fun fetchTvShow(showId: Int): LiveData<TvShowModel> {
        val responseMediatorLiveData = MediatorLiveData<TvShowModel>()
        responseMediatorLiveData.addSource(cloudDataSource.getTvShow(showId)) { response ->
            if (response != null && response.isSuccessful && response.body != null) {
                responseMediatorLiveData.postValue(dataMapper.convert(response.body))
            } else {
                responseMediatorLiveData.postValue(
                    dataMapper.createDomainModel(
                        response.code,
                        TvShowModel::class.java
                    )
                )
            }
        }
        return responseMediatorLiveData
    }

    override fun loadTvShow(showId: Int): LiveData<TvShowModel> = Transformations.map(
        diskDataSource.selectTvShow(showId)
    ) {
        dataMapper.convert(it)
    }


    override fun insertTvShows(tvShows: List<TvShowModel>): List<Long> =
        diskDataSource.insertTvShows(tvShows.map { dataMapper.convert(it) })

    override fun insertTvShow(tvShow: TvShowModel): Long =
        diskDataSource.insertTvShow(dataMapper.convert(tvShow))
}