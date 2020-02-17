package io.jcal.movies_provider.domain.interactor

import io.jcal.movies_provider.domain.interactor.base.NetworkBoundResourceFlowCoroutines
import io.jcal.movies_provider.repository.MDBRepository
import io.jcal.movies_provider.repository.api.network.HttpBaseValues
import io.jcal.movies_provider.repository.mapper.model.MoviesModel
import javax.inject.Inject

class UseCasePopularMoviesFlow @Inject constructor(
    private val repository: MDBRepository
) : NetworkBoundResourceFlowCoroutines<MoviesModel, UseCasePopularMoviesFlow.Params>() {

    override suspend fun saveCallResult(item: MoviesModel) {
        repository.insertMoviesCoroutines(item)
    }

    override suspend fun loadFromDb(params: Params): MoviesModel =
        repository.loadPopularMoviesCoroutines()

    override suspend fun createCall(params: Params): MoviesModel =
        repository.getPopularMovies(params.language, params.page)

    override fun getLoadingObject(): MoviesModel = MoviesModel()

    data class Params constructor(
        val language: String = HttpBaseValues.LANGUAGE,
        val page: Int = HttpBaseValues.PAGE
    )
}
