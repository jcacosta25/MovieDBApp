package io.jcal.theMovie.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import io.jcal.movies_provider.domain.interactor.UseCaseTvShow
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.TvShowUIModel
import javax.inject.Inject

class TvShowDetailViewModel @Inject constructor(
    private val useCaseTvShow: UseCaseTvShow,
    private val mapper: PresentationDataMapper
) : ViewModel() {

    private val tvShow: MediatorLiveData<TvShowUIModel> = MediatorLiveData()

    private val tvShowId: MutableLiveData<Int> = MutableLiveData()

    init {
        tvShow.addSource(
            switchMap(tvShowId) { tvShowId ->
                map(
                    useCaseTvShow.execute(
                        UseCaseTvShow.Params(tvShowId)
                    )
                ) { response ->
                    mapper.convert(response)
                }
            }
        ) { tvShowDetail ->
            tvShow.postValue(tvShowDetail)
        }
    }

    fun getTvShow(tvShowId: Int) {
        this.tvShowId.postValue(tvShowId)
    }

    fun tvShowDetail(): LiveData<TvShowUIModel> = tvShow
}
