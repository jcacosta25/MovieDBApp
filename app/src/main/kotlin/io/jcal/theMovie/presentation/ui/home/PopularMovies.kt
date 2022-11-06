package io.jcal.theMovie.presentation.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import io.jcal.theMovie.presentation.mapper.model.MovieUIModel
import io.jcal.theMovie.presentation.ui.adapter.MovieCard
import io.jcal.theMovie.presentation.ui.home.viewmodel.MoviesViewModel

@Composable
fun PopularMoviesList(
    viewModel: MoviesViewModel = viewModel(),
    navigateToMovie: (MovieUIModel) -> Unit = { _ -> }
) {
	val lazyMoviesPopular = viewModel.movies.collectAsLazyPagingItems()
	LazyColumn(
		verticalArrangement = Arrangement.spacedBy(8.dp),
		contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
	) {
		items(items = lazyMoviesPopular, itemContent = {
			MovieCard(movie = it!!, navigateToMovie = navigateToMovie)
		})
	}
}