package io.jcal.theMovie.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import io.jcal.movies_provider.domain.interactor.UseCasePopularMovies
import io.jcal.movies_provider.domain.interactor.UseCasePopularMoviesCR
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.MovieUIModelList
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val useCasePopularMovies: UseCasePopularMovies,
    private val useCasePopularMoviesCR: UseCasePopularMoviesCR,
    private val mapper: PresentationDataMapper
) : ViewModel() {

    private val movies: MediatorLiveData<MovieUIModelList> = MediatorLiveData()

    val popularMoviesCoroutines by lazy {
        map(useCasePopularMoviesCR.execute(UseCasePopularMoviesCR.Params())) {
            mapper.convert(it)
        }
    }

    fun popularMovies(): LiveData<MovieUIModelList> {
        if (movies.value == null) {
            movies.addSource(map(useCasePopularMovies.execute(UseCasePopularMovies.Params())) { response ->
                mapper.convert(response.data!!)
            }) { movieList ->
                movies.postValue(movieList)
            }
        }
        return movies
    }

    fun popularMovies(movies: MovieUIModelList) {
        this.movies.postValue(movies)
    }
}