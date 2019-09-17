package io.jcal.movies_provider.domain.interactor

import androidx.lifecycle.LiveData
import io.jcal.movies_provider.domain.interactor.base.RepositoryResource
import io.jcal.movies_provider.repository.MDBRepository
import io.jcal.movies_provider.repository.api.network.HttpBaseValues
import io.jcal.movies_provider.repository.mapper.model.MovieModel
import javax.inject.Inject

class UseCaseMovie @Inject constructor(
    private val repository: MDBRepository
) : RepositoryResource<MovieModel, UseCaseMovie.Params>() {

    override fun saveCallResult(item: MovieModel) {
        repository.insertMovie(item)
    }

    override fun loadFromDb(params: Params): LiveData<MovieModel> =
        repository.loadMovie(params.movieId)

    override suspend fun createCall(params: Params): MovieModel =
        repository.getMovie(params.movieId)

    override fun getLoadingObject(): MovieModel = MovieModel()

    data class Params constructor(
        val movieId: Int = HttpBaseValues.BASE_ID,
        val language: String = HttpBaseValues.LANGUAGE
    )
}