package io.jcal.movies_provider.domain.interactor

import io.jcal.movies_provider.domain.interactor.base.NetworkBoundResource
import io.jcal.movies_provider.repository.MDBRepository
import io.jcal.movies_provider.repository.api.network.HttpBaseValues
import io.jcal.movies_provider.repository.mapper.model.MovieModel
import javax.inject.Inject

class UseCaseGetMovie @Inject constructor(
    private val repository: MDBRepository
) : NetworkBoundResource<MovieModel, UseCaseGetMovie.Params>() {
	
	override suspend fun saveCallResult(item: MovieModel) {
		item.state
	}
	
	override suspend fun loadFromDb(params: Params): MovieModel =
		repository.loadMovie(params.movieId)
	
	override val parameters: Params
		get() = Params()
	
	override suspend fun createCall(params: Params): MovieModel =
		repository.fetchMovie(params.movieId)
	
	override fun getLoadingObject(): MovieModel = MovieModel()
	
	data class Params constructor(
	    val movieId: Int = HttpBaseValues.BASE_ID,
	    val language: String = HttpBaseValues.LANGUAGE
	)
}
