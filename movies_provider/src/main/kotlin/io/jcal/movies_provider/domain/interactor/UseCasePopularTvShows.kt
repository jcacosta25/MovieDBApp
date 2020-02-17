package io.jcal.movies_provider.domain.interactor

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.domain.interactor.base.NetworkBoundResourceLiveDataCoroutines
import io.jcal.movies_provider.repository.MDBRepository
import io.jcal.movies_provider.repository.api.network.HttpBaseValues
import io.jcal.movies_provider.repository.mapper.model.TvShowsModel
import javax.inject.Inject

class UseCasePopularTvShows @Inject constructor(
    private val repository: MDBRepository
) : NetworkBoundResourceLiveDataCoroutines<TvShowsModel, UseCasePopularTvShows.Params>() {

    override fun saveCallResult(item: TvShowsModel) {
        repository.insertTvShows(item)
    }

    override fun loadFromDb(params: Params): LiveData<TvShowsModel> = repository.loadPopularShows()

    override suspend fun createCall(params: Params): TvShowsModel = repository.getPopularShows()

    override fun getLoadingObject(): TvShowsModel = TvShowsModel()

    data class Params constructor(
        val language: String = HttpBaseValues.LANGUAGE,
        val page: Int = HttpBaseValues.PAGE
    )
}
