package io.jcal.theMovie.presentation.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.jcal.provider.domain.interactor.UseCaseTvShow
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.TvShowUIModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowDetailViewModel @Inject constructor(
    private val useCaseTvShow: UseCaseTvShow,
    private val mapper: PresentationDataMapper
) : ViewModel() {
	
	private val tvShow: MediatorLiveData<TvShowUIModel> = MediatorLiveData()
	
	fun getTvShow(tvShowId: Int) {
		viewModelScope.launch {
			useCaseTvShow.execute(UseCaseTvShow.Params(tvShowId)).collect {
				tvShow.postValue(mapper.convert(it))
			}
		}
	}
	
	fun tvShowDetail(): LiveData<TvShowUIModel> = tvShow
}
