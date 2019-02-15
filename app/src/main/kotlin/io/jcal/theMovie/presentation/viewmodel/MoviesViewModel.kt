package io.jcal.theMovie.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.jcal.movies_provider.domain.interactor.UseCasePopularMovies
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.MovieUIModelList
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val useCasePopularMovies: UseCasePopularMovies,
    private val mapper: PresentationDataMapper
) : ViewModel() {

    private val movies: MediatorLiveData<MovieUIModelList> = MediatorLiveData()

    fun popularMovies(): LiveData<MovieUIModelList> {
        if (movies.value == null) {
            movies.addSource(Transformations.map(useCasePopularMovies.asLiveData()) { response ->
                mapper.convert(response.data)
            }) { movieList ->
                movies.postValue(movieList)
            }
        }
        return movies
    }
}