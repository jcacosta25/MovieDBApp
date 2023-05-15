package io.jcal.theMovie.presentation.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.jcal.provider.domain.interactor.GetPopularMovieList
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.MovieUIModel
import io.jcal.theMovie.presentation.ui.home.viewmodel.paging.MoviesDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
	private val mapper: PresentationDataMapper,
	private val moviesListCase: GetPopularMovieList.UseCase
) : ViewModel() {
	val movies: Flow<PagingData<MovieUIModel>> = Pager(PagingConfig(pageSize = 20)) {
		MoviesDataSource(moviesListCase, mapper)
	}.flow
}
