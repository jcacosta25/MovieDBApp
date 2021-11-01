package io.jcal.movies_provider.domain.interactor

import io.jcal.movies_provider.domain.interactor.base.NetworkBoundResource
import io.jcal.movies_provider.repository.MDBRepository
import io.jcal.movies_provider.repository.api.network.HttpBaseValues
import io.jcal.movies_provider.repository.mapper.model.TvShowModel
import javax.inject.Inject

class UseCaseTvShow @Inject constructor(
    private val repository: MDBRepository
) : NetworkBoundResource<TvShowModel, UseCaseTvShow.Params>() {
	
	override val parameters: Params
		get() = Params()
	
	override suspend fun saveCallResult(item: TvShowModel) {
		repository.insertTvShow(item)
	}
	
	override suspend fun loadFromDb(params: Params): TvShowModel =
		repository.loadShow(params.tvShowId)
	
	override suspend fun createCall(params: Params): TvShowModel =
		repository.fetchShow(params.tvShowId)
	
	override fun getLoadingObject(): TvShowModel = TvShowModel()
	
	data class Params constructor(
	    val tvShowId: Int = HttpBaseValues.BASE_ID,
	    val language: String = HttpBaseValues.LANGUAGE
	)
}
