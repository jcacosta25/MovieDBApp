package io.jcal.theMovie.presentation.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.jcal.movies_provider.domain.interactor.UseCasePopularTvShows
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.TvShowUIModel
import io.jcal.theMovie.presentation.ui.home.viewmodel.paging.Listing
import io.jcal.theMovie.presentation.ui.home.viewmodel.paging.ShowsDataSourceFactory
import javax.inject.Inject

class TvShowsViewModel @Inject constructor(
    useCase: UseCasePopularTvShows,
    mapper: PresentationDataMapper
) : ViewModel() {
	
	private val source = ShowsDataSourceFactory(
		viewModelScope,
		useCase,
		mapper
	)
	
	private val listing = Listing<TvShowUIModel>(
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
	
	val tvShows: LiveData<PagedList<TvShowUIModel>> = listing.pagedList
}
