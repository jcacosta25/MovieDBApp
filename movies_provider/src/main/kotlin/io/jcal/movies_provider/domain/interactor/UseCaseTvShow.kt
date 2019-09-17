package io.jcal.movies_provider.domain.interactor

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.domain.interactor.base.RepositoryResource
import io.jcal.movies_provider.repository.MDBRepository
import io.jcal.movies_provider.repository.api.network.HttpBaseValues
import io.jcal.movies_provider.repository.mapper.model.TvShowModel
import javax.inject.Inject

class UseCaseTvShow @Inject constructor(
    private val repository: MDBRepository
) : RepositoryResource<TvShowModel, UseCaseTvShow.Params>() {


    override fun saveCallResult(item: TvShowModel) {
        repository.insertTvShow(item)
    }


    override fun loadFromDb(params: Params): LiveData<TvShowModel> =
        repository.loadShow(params.tvShowId)

    override suspend fun createCall(params: Params): TvShowModel =
        repository.getShow(params.tvShowId)

    override fun getLoadingObject(): TvShowModel = TvShowModel()

    data class Params constructor(
        val tvShowId: Int = HttpBaseValues.BASE_ID,
        val language: String = HttpBaseValues.LANGUAGE
    )

}