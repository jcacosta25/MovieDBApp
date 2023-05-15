package io.jcal.theMovie.presentation.ui.series

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import io.jcal.provider.repository.mapper.orEmpty
import io.jcal.theMovie.R
import io.jcal.theMovie.presentation.mapper.model.TvShowUIModel

@Composable
fun TvShowDetailScreen(
    tvShowId: Int? = null,
    viewModel: TvShowDetailViewModel = hiltViewModel(),
) {
	viewModel.getTvShow(tvShowId.orEmpty())
	TvShowDetail(viewModel)
}

@Preview
@Composable
fun TvShowDetail(
    viewModel: TvShowDetailViewModel = viewModel(),
) {
	val tvShow =
		viewModel.tvShowDetail().collectAsStateWithLifecycle(initialValue = TvShowUIModel())
	ConstraintLayout(
		modifier = Modifier
			.background(colorResource(id = R.color.colorSurface))
			.fillMaxWidth(),
	) {
		val (backdrop, poster, title, releaseDate, overviewTitle, overviewText) = createRefs()
		AsyncImage(
			model = ImageRequest.Builder(LocalContext.current)
				.data(tvShow.value.backdropPath)
				.size(Size.ORIGINAL)
				.crossfade(true)
				.build(),
			contentDescription = null,
			placeholder = painterResource(id = R.drawable.placeholder_drawable),
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.height(224.dp)
				.fillMaxWidth()
				.constrainAs(backdrop) {
					top.linkTo(parent.top)
					start.linkTo(parent.start)
					end.linkTo(parent.end)
					width = Dimension.fillToConstraints
					height = Dimension.preferredWrapContent
				},
		)
		
		AsyncImage(
			model = ImageRequest.Builder(LocalContext.current)
				.data(tvShow.value.posterPath)
				.crossfade(true)
				.build(),
			contentDescription = null,
			placeholder = painterResource(id = R.drawable.placeholder_drawable),
			modifier = Modifier
				.width(128.dp)
				.height(225.dp)
				.constrainAs(poster) {
					val negative = -56
					start.linkTo(parent.start, 16.dp)
					top.linkTo(backdrop.bottom, negative.dp)
				},
		)
		
		Text(
			text = tvShow.value.name,
			textAlign = TextAlign.Start,
			style = MaterialTheme.typography.titleSmall,
			fontWeight = FontWeight.Bold,
			modifier = Modifier
				.fillMaxWidth()
				.constrainAs(title) {
					top.linkTo(backdrop.bottom, margin = 16.dp)
					start.linkTo(poster.end, margin = 16.dp)
					end.linkTo(parent.end, margin = 16.dp)
					width = Dimension.fillToConstraints
				},
			color = colorResource(id = R.color.colorOnSurface),
		)
		
		Text(
			text = tvShow.value.airDate,
			style = MaterialTheme.typography.labelSmall,
			textAlign = TextAlign.Start,
			modifier = Modifier
				.fillMaxWidth()
				.constrainAs(releaseDate) {
					start.linkTo(poster.end, margin = 16.dp)
					end.linkTo(parent.end, margin = 16.dp)
					top.linkTo(title.bottom, margin = 8.dp)
					width = Dimension.fillToConstraints
				},
			color = colorResource(id = R.color.colorOnSurface),
		)
		
		Text(
			text = stringResource(id = R.string.overview_title),
			style = MaterialTheme.typography.titleSmall,
			textAlign = TextAlign.Start,
			fontWeight = FontWeight.Bold,
			modifier = Modifier.constrainAs(overviewTitle) {
				start.linkTo(parent.start, margin = 16.dp)
				end.linkTo(parent.end, margin = 16.dp)
				top.linkTo(poster.bottom, margin = 16.dp)
				width = Dimension.fillToConstraints
			},
			color = colorResource(id = R.color.colorOnSurface),
		)
		
		Text(
			text = tvShow.value.overview,
			style = MaterialTheme.typography.bodyMedium,
			textAlign = TextAlign.Start,
			modifier = Modifier.constrainAs(overviewText) {
				start.linkTo(parent.start, margin = 16.dp)
				end.linkTo(parent.end, margin = 16.dp)
				top.linkTo(overviewTitle.bottom, margin = 8.dp)
				width = Dimension.fillToConstraints
			},
			color = colorResource(id = R.color.colorOnSurface),
		)
	}
}