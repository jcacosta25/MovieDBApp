package io.jcal.movies_provider.domain.interactor

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.domain.executors.AppExecutors
import io.jcal.movies_provider.domain.interactor.base.NetworkBoundResource
import io.jcal.movies_provider.domain.interactor.base.NetworkUtil
import io.jcal.movies_provider.repository.Repository
import io.jcal.movies_provider.repository.mapper.model.TvShowsModel
import javax.inject.Inject

class UseCasePopularTvShows @Inject constructor(
    appExecutors: AppExecutors,
    private val repository: Repository,
    private val utils: NetworkUtil
) : NetworkBoundResource<TvShowsModel>(appExecutors) {
    init {
        init(TvShowsModel())
    }

    override fun saveCallResult(item: TvShowsModel) {
        repository.insertTvShows(item.results)
    }

    override fun shouldFetch(data: TvShowsModel?): Boolean = utils.isConnected

    override fun loadFromDb(): LiveData<TvShowsModel> = repository.loadAllTvShows()

    override fun createCall(): LiveData<TvShowsModel> = repository.fetchPopularTvShows()
}