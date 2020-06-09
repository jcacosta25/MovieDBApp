package io.jcal.movies_provider.domain.interactor

import io.jcal.movies_provider.domain.interactor.base.NetworkBoundResource
import io.jcal.movies_provider.repository.MDBRepository
import io.jcal.movies_provider.repository.api.network.HttpBaseValues
import io.jcal.movies_provider.repository.mapper.model.MoviesModel
import javax.inject.Inject

class UseCaseGetPopularMovies @Inject constructor(
    private val repository: MDBRepository
) : NetworkBoundResource<MoviesModel, UseCaseGetPopularMovies.Params>() {

    override suspend fun saveCallResult(item: MoviesModel) {
        repository.insertMoviesCoroutines(item)
    }

    override suspend fun loadFromDb(params: Params): MoviesModel =
        repository.loadPopularMovies()

    override suspend fun createCall(params: Params): MoviesModel =
        repository.fetchPopularMovies(params.language, params.page)

    override fun getLoadingObject(): MoviesModel = MoviesModel()

    override val parameters: Params
        get() = Params()

    data class Params constructor(
        val page: Int = HttpBaseValues.PAGE,
        val language: String = HttpBaseValues.LANGUAGE
    )
}
