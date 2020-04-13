package io.jcal.theMovie.presentation.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.jcal.movies_provider.domain.interactor.UseCasePopularMoviesFlow
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.MovieUIModelList
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val useCasePopularMoviesFlow: UseCasePopularMoviesFlow,
    private val mapper: PresentationDataMapper
) : ViewModel() {
    val popularMovies: MediatorLiveData<MovieUIModelList> = MediatorLiveData()

    init {
        viewModelScope.launch {
            useCasePopularMoviesFlow.execute(UseCasePopularMoviesFlow.Params()).collect {
                popularMovies.postValue(mapper.convert(it))
            }
        }
    }

    fun popularMovies(movies: MovieUIModelList) {
        this.popularMovies.postValue(movies)
    }

    fun nextPopularMovies(page: Int) {
        viewModelScope.launch {
            useCasePopularMoviesFlow.execute(UseCasePopularMoviesFlow.Params(page = page)).collect {
                TODO("Work with Pagination with coroutines and flow")
            }
        }
    }
}
