package io.jcal.theMovie.presentation.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.jcal.movies_provider.domain.interactor.UseCasePopularMoviesFlow
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.MovieUIModel
import io.jcal.theMovie.presentation.ui.home.MoviesDataSourceFactory
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val useCasePopularMoviesFlow: UseCasePopularMoviesFlow,
    private val mapper: PresentationDataMapper
) : ViewModel() {

    val moviesLiveData: LiveData<PagedList<MovieUIModel>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()

        moviesLiveData = LivePagedListBuilder<Int, MovieUIModel>(
            MoviesDataSourceFactory(
                viewModelScope,
                useCasePopularMoviesFlow,
                mapper
            ), config
        ).build()
    }
}
