package io.jcal.theMovie.presentation.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import io.jcal.movies_provider.domain.interactor.UseCasePopularTvShows
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.TvShowUIList
import javax.inject.Inject

class TvShowsViewModel @Inject constructor(
    private val useCasePopularTvShows: UseCasePopularTvShows,
    private val mapper: PresentationDataMapper
) : ViewModel() {

    private val tvShows: MediatorLiveData<TvShowUIList> = MediatorLiveData()

    fun popularTvShows(): LiveData<TvShowUIList> {
        if (tvShows.value == null) {
            tvShows.addSource(
                map(
                    useCasePopularTvShows.execute(
                        UseCasePopularTvShows.Params()
                    )
                ) { response ->
                    mapper.convert(response)
                }) { source -> tvShows.postValue(source) }
        }
        return tvShows
    }

    fun popularTvShows(tvShows: TvShowUIList) {
        this.tvShows.postValue(tvShows)
    }
}
