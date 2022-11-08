package io.jcal.theMovie.presentation.ui.home.viewmodel.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.jcal.provider.domain.interactor.GetPopularMovieList
import io.jcal.provider.domain.interactor.GetPopularTvShowsList
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.MovieUIModel
import io.jcal.theMovie.presentation.mapper.model.TvShowUIModel

class MoviesDataSource(
	private val useCase: GetPopularMovieList.UseCase,
	private val mapper: PresentationDataMapper
) : PagingSource<Int, MovieUIModel>() {
	
	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieUIModel> {
		return try {
			val nextPage = params.key ?: 1
			val previousPage = if (nextPage == 1) null else nextPage.dec()
			val movieListResponse = useCase.invoke(GetPopularMovieList.Params(nextPage))
			LoadResult.Page(
				data = movieListResponse.results.map { mapper.convert(it) },
				prevKey = previousPage,
				nextKey = movieListResponse.page.inc().takeIf { it != nextPage }
			)
		} catch (e: Exception) {
			LoadResult.Error(e)
		}
	}
	
	override fun getRefreshKey(state: PagingState<Int, MovieUIModel>): Int? =
		state.anchorPosition
}

class TvShowsDataSource(
	private val useCase: GetPopularTvShowsList.UseCase,
	private val mapper: PresentationDataMapper
) : PagingSource<Int, TvShowUIModel>() {
	
	override fun getRefreshKey(state: PagingState<Int, TvShowUIModel>): Int? = state.anchorPosition
	
	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShowUIModel> =
		try {
			val nextPage = params.key ?: 1
			val previousPage = if (nextPage == 1) {
				null
			} else {
				nextPage.dec()
			}
			val showsResponse = useCase.invoke(GetPopularTvShowsList.Params(nextPage))
			LoadResult.Page(
				data = showsResponse.results.map { mapper.convert(it) },
				prevKey = previousPage,
				nextKey = showsResponse.page.inc().takeIf { it != nextPage }
			)
		} catch (e: Exception) {
			LoadResult.Error(e)
		}
}