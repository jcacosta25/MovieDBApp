package io.jcal.provider.domain.interactor

import io.jcal.provider.repository.MDBRepository
import io.jcal.provider.repository.api.network.HttpBaseValues
import io.jcal.provider.repository.mapper.model.MoviesModel
import javax.inject.Inject

interface GetPopularMovieList {
	
	interface UseCase {
		suspend fun invoke(params: Params = Params()): MoviesModel
	}
	
	data class Params constructor(
		val page: Int = HttpBaseValues.PAGE,
		val language: String = HttpBaseValues.LANGUAGE
	)
}

class InternalGetPopularMovies @Inject constructor(
	private val repository: MDBRepository
) : GetPopularMovieList.UseCase {
	
	override suspend fun invoke(params: GetPopularMovieList.Params): MoviesModel =
		repository.fetchPopularMovies(params.language, params.page)
}