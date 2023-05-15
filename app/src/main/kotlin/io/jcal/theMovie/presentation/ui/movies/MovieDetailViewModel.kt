package io.jcal.theMovie.presentation.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.jcal.provider.domain.interactor.UseCaseGetMovie
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.MovieUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val useCaseGetMovie: UseCaseGetMovie,
    private val mapper: PresentationDataMapper
) : ViewModel() {
	
	private val movie: MutableStateFlow<MovieUIModel> = MutableStateFlow(MovieUIModel())
	
	fun getMovie(movieId: Int) {
		viewModelScope.launch {
			useCaseGetMovie.execute(UseCaseGetMovie.Params(movieId)).collect {
				movie.emit(mapper.convert(it))
			}
		}
	}
	
	fun movieDetail(): Flow<MovieUIModel> = movie
}
