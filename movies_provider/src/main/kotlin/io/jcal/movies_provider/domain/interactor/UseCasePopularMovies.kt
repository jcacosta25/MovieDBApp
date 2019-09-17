package io.jcal.movies_provider.domain.interactor

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.domain.interactor.base.RepositoryResource
import io.jcal.movies_provider.repository.MDBRepository
import io.jcal.movies_provider.repository.api.network.HttpBaseValues
import io.jcal.movies_provider.repository.mapper.model.MoviesModel
import javax.inject.Inject

class UseCasePopularMovies @Inject constructor(
    private val repository: MDBRepository
) : RepositoryResource<MoviesModel, UseCasePopularMovies.Params>() {

    override fun saveCallResult(item: MoviesModel) {
        repository.insertAllMovies(item)
    }

    override fun loadFromDb(params: Params): LiveData<MoviesModel> = repository.loadPopularMovies()

    override suspend fun createCall(params: Params): MoviesModel =
        repository.getPopularMovies(params.language, params.page)

    override fun getLoadingObject(): MoviesModel = MoviesModel()

    data class Params constructor(
        val language: String = HttpBaseValues.LANGUAGE,
        val page: Int = HttpBaseValues.PAGE
    )
}