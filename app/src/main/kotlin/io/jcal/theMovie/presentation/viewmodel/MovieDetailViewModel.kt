package io.jcal.theMovie.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.jcal.movies_provider.domain.interactor.UseCaseMovie
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.MovieUIModel
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val useCaseMovie: UseCaseMovie,
    private val mapper: PresentationDataMapper
) : ViewModel() {

    private val movie: MediatorLiveData<MovieUIModel> = MediatorLiveData()

    private val movieId: MutableLiveData<Int> = MutableLiveData()

    init {
        movie.addSource(
            Transformations.switchMap(movieId) { movieId ->
                Transformations.map(
                    useCaseMovie.execute(
                        UseCaseMovie.Params(movieId)
                    )
                ) { response ->
                    mapper.convert(response.data!!)
                }
            }
        ) { movieDetail ->
            movie.postValue(movieDetail)
        }
    }

    fun getMovie(movieId: Int) {
        this.movieId.postValue(movieId)
    }

    fun movieDetail(): LiveData<MovieUIModel> = movie
}