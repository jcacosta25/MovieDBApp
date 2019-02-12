package io.jcal.movies_provider.domain.interactor

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.domain.executors.AppExecutors
import io.jcal.movies_provider.domain.interactor.base.NetworkBoundResource
import io.jcal.movies_provider.domain.interactor.base.NetworkUtil
import io.jcal.movies_provider.repository.Repository
import io.jcal.movies_provider.repository.mapper.model.MoviesModel
import javax.inject.Inject

class UseCasePopularMovies @Inject constructor(
    appExecutors: AppExecutors,
    private val repository: Repository,
    private val utils: NetworkUtil
) : NetworkBoundResource<MoviesModel>(appExecutors) {
    init {
        init(MoviesModel())
    }

    override fun saveCallResult(item: MoviesModel) {
        repository.insertMovies(item.results)
    }

    override fun shouldFetch(data: MoviesModel?): Boolean = utils.isConnected

    override fun loadFromDb(): LiveData<MoviesModel> = repository.loadAllMovies()

    override fun createCall(): LiveData<MoviesModel> = repository.fetchPopularMovies()
}