package io.jcal.movies_provider.domain.interactor

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.domain.executors.AppExecutors
import io.jcal.movies_provider.domain.interactor.base.NetworkBoundResource
import io.jcal.movies_provider.domain.interactor.base.NetworkUtil
import io.jcal.movies_provider.repository.Repository
import io.jcal.movies_provider.repository.api.network.HttpBaseValues
import io.jcal.movies_provider.repository.mapper.model.TvShowModel
import javax.inject.Inject

class UseCaseTvShow @Inject constructor(
    appExecutors: AppExecutors,
    private val repository: Repository,
    private val utils: NetworkUtil
) : NetworkBoundResource<TvShowModel, UseCaseTvShow.Params>(appExecutors) {


    override fun saveCallResult(item: TvShowModel) {
        repository.insertTvShow(item)
    }

    override fun shouldFetch(data: TvShowModel?): Boolean = utils.isConnected

    override fun loadFromDb(params: Params): LiveData<TvShowModel> =
        repository.loadTvShow(params.tvShowId)

    override fun createCall(params: Params): LiveData<TvShowModel> =
        repository.fetchTvShow(params.tvShowId)

    override fun getLoadingObject(): TvShowModel = TvShowModel()

    data class Params constructor(
        val tvShowId: Int = HttpBaseValues.BASE_ID,
        val language: String = HttpBaseValues.LANGUAGE
    )

}