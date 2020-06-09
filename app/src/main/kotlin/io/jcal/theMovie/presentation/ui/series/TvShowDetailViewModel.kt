package io.jcal.theMovie.presentation.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.jcal.movies_provider.domain.interactor.UseCaseTvShow
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.TvShowUIModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

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
