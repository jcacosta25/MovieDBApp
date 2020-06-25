package io.jcal.theMovie.presentation.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.jcal.movies_provider.domain.interactor.UseCaseGetPopularMovies
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.MovieUIModel
import io.jcal.theMovie.presentation.ui.home.viewmodel.paging.Listing
import io.jcal.theMovie.presentation.ui.home.viewmodel.paging.MoviesDataSourceFactory
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    useCase: UseCaseGetPopularMovies,
    mapper: PresentationDataMapper
) : ViewModel() {

    private val source =
        MoviesDataSourceFactory(
            viewModelScope,
            useCase,
            mapper
        )

    private val listing = Listing<MovieUIModel>(
        pagedList = LivePagedListBuilder(
            source,
            PagedList.Config.Builder()
                .setPageSize(30)
                .setEnablePlaceholders(false)
                .build()
        ).build(),
        resourceState = source.dataSource.switchMap { it.resourceState },
        refreshState = source.dataSource.switchMap { it.refreshState },
        refresh = {
            source.invalidateDataSource()
        },
        retry = {
            source.retryAllField()
        },
        clearCoroutineJobs = {
            source.clear()
        }
    )

    val moviesLiveData: LiveData<PagedList<MovieUIModel>> = listing.pagedList
}
