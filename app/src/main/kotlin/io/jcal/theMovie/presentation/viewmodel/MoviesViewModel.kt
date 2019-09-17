package io.jcal.theMovie.presentation.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import io.jcal.movies_provider.domain.interactor.UseCasePopularMovies
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.MovieUIModelList
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val useCasePopularMovies: UseCasePopularMovies,
    private val mapper: PresentationDataMapper
) : ViewModel() {

    private val initMovies by lazy {
        map(useCasePopularMovies.execute(UseCasePopularMovies.Params())) {
            mapper.convert(it)
        }
    }

    val popularMovies: MediatorLiveData<MovieUIModelList> = MediatorLiveData()

    init {
        popularMovies.addSource(initMovies) {
            popularMovies.postValue(it)
        }
    }

    fun popularMovies(movies: MovieUIModelList) {
        this.popularMovies.postValue(movies)
    }

    fun nextPopularMovies(page: Int) {
    }
}