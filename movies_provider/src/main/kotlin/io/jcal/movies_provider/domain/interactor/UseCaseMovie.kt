package io.jcal.movies_provider.domain.interactor

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.domain.executors.AppExecutors
import io.jcal.movies_provider.domain.interactor.base.NetworkBoundResource
import io.jcal.movies_provider.domain.interactor.base.NetworkUtil
import io.jcal.movies_provider.repository.Repository
import io.jcal.movies_provider.repository.api.network.HttpBaseValues
import io.jcal.movies_provider.repository.mapper.model.MovieModel
import javax.inject.Inject

class UseCaseMovie @Inject constructor(
    appExecutors: AppExecutors,
    private val repository: Repository,
    private val utils: NetworkUtil
) : NetworkBoundResource<MovieModel, UseCaseMovie.Params>(appExecutors) {

    override fun saveCallResult(item: MovieModel) {
        repository.insertMovie(item)
    }

    override fun shouldFetch(data: MovieModel?): Boolean = utils.isConnected

    override fun loadFromDb(params: Params): LiveData<MovieModel> =
        repository.loadMovie(params.movieId)

    override fun createCall(params: Params): LiveData<MovieModel> =
        repository.fetchMovie(params.movieId)

    override fun getLoadingObject(): MovieModel = MovieModel()

    data class Params constructor(
        val movieId: Int = HttpBaseValues.BASE_ID,
        val language: String = HttpBaseValues.LANGUAGE
    )
}