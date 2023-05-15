package io.jcal.theMovie.presentation.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.jcal.provider.domain.interactor.GetPopularTvShowsList
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.TvShowUIModel
import io.jcal.theMovie.presentation.ui.home.viewmodel.paging.TvShowsDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(
	useCase: GetPopularTvShowsList.UseCase,
	mapper: PresentationDataMapper
) : ViewModel() {
	val tvShows: Flow<PagingData<TvShowUIModel>> = Pager(PagingConfig(pageSize = 20)) {
		TvShowsDataSource(useCase, mapper)
	}.flow
}
