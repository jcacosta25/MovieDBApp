package io.jcal.movies_provider.domain.interactor

import io.jcal.movies_provider.domain.interactor.base.NetworkBoundResource
import io.jcal.movies_provider.repository.MDBRepository
import io.jcal.movies_provider.repository.api.network.HttpBaseValues
import io.jcal.movies_provider.repository.mapper.model.TvShowsModel
import javax.inject.Inject

class UseCasePopularTvShows @Inject constructor(
	private val repository: MDBRepository
) : NetworkBoundResource<TvShowsModel, UseCasePopularTvShows.Params>() {
	
	override suspend fun saveCallResult(item: TvShowsModel) {
		repository.insertTvShows(item)
	}
	
	override suspend fun loadFromDb(params: Params): TvShowsModel =
		repository.loadPopularShows()
	
	override val parameters: Params
		get() = Params()
	
	override suspend fun createCall(params: Params): TvShowsModel =
		repository.fetchPopularShows(params.language, params.page)
	
	override fun getLoadingObject(): TvShowsModel = TvShowsModel()
	
	data class Params constructor(
		val page: Int = HttpBaseValues.PAGE,
		val language: String = HttpBaseValues.LANGUAGE
	)
}
