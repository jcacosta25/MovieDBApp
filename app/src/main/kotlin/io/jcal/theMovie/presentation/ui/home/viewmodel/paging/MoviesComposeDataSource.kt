package io.jcal.theMovie.presentation.ui.home.viewmodel.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.jcal.provider.domain.interactor.GetPopularMovieList
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.MovieUIModel

class MoviesComposeDataSource(
	private val useCase: GetPopularMovieList.UseCase,
	private val mapper: PresentationDataMapper
) : PagingSource<Int, MovieUIModel>() {
	
	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieUIModel> {
		return try {
			val nextPage = params.key ?: 1
			val movieListResponse = useCase.invoke(GetPopularMovieList.Params(nextPage))
			LoadResult.Page(
				data = movieListResponse.results.map { mapper.convert(it) },
				prevKey = if (nextPage == 1) null else nextPage.dec(),
				nextKey = movieListResponse.page.inc()
			)
		} catch (e: Exception) {
			LoadResult.Error(e)
		}
	}
	
	override fun getRefreshKey(state: PagingState<Int, MovieUIModel>): Int? =
		state.anchorPosition
}