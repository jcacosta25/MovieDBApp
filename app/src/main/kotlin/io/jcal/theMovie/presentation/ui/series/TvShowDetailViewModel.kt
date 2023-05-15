package io.jcal.theMovie.presentation.ui.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.jcal.provider.domain.interactor.UseCaseTvShow
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.TvShowUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowDetailViewModel @Inject constructor(
    private val useCaseTvShow: UseCaseTvShow,
    private val mapper: PresentationDataMapper
) : ViewModel() {
	
	private val tvShow: MutableStateFlow<TvShowUIModel> = MutableStateFlow(TvShowUIModel())
	
	fun getTvShow(tvShowId: Int) {
		viewModelScope.launch {
			useCaseTvShow.execute(UseCaseTvShow.Params(tvShowId)).collect {
				tvShow.emit(mapper.convert(it))
			}
		}
	}
	
	fun tvShowDetail(): Flow<TvShowUIModel> = tvShow
}
