package io.jcal.theMovie.presentation.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.jcal.movies_provider.domain.interactor.UseCaseGetMovie
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.MovieUIModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
	private val useCaseGetMovie: UseCaseGetMovie,
	private val mapper: PresentationDataMapper
) : ViewModel() {
	
	private val movie: MediatorLiveData<MovieUIModel> = MediatorLiveData()
	
	fun getMovie(movieId: Int) {
		viewModelScope.launch {
			useCaseGetMovie.execute(UseCaseGetMovie.Params(movieId)).collect {
				movie.postValue(mapper.convert(it))
			}
		}
	}
	
	fun movieDetail(): LiveData<MovieUIModel> = movie
}
