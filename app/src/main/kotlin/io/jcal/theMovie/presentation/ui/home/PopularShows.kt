package io.jcal.theMovie.presentation.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import io.jcal.theMovie.presentation.mapper.model.TvShowUIModel
import io.jcal.theMovie.presentation.ui.adapter.TvShowCard
import io.jcal.theMovie.presentation.ui.home.viewmodel.TvShowsViewModel

@Composable
fun PopularShowsList(
    navigateToTvShow: (TvShowUIModel) -> Unit = { _ -> },
    viewModel: TvShowsViewModel = hiltViewModel(),
) {
	val lazyPopularTvShows = viewModel.tvShows.collectAsLazyPagingItems()
	
	LazyColumn(
		verticalArrangement = Arrangement.spacedBy(8.dp),
		contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
	) {
		items(
		count = lazyPopularTvShows.itemCount,
		key = lazyPopularTvShows.itemKey(),
		contentType = lazyPopularTvShows.itemContentType(),
	) { index ->
		val item = lazyPopularTvShows[index]
		TvShowCard(tvShow = item!!, navigateToTvShow)
		}
	}
}