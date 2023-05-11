package io.jcal.theMovie.presentation.ui.adapter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.jcal.theMovie.R
import io.jcal.theMovie.presentation.mapper.model.TvShowUIModel
import io.jcal.theMovie.presentation.ui.previews.TvShowDataProvider

@Preview
@Composable
fun TvShowCard(
    @PreviewParameter(TvShowDataProvider::class)
    tvShow: TvShowUIModel,
    navigateToShow: (TvShowUIModel) -> Unit = { _ -> },
) {
	Card(
		modifier = Modifier
			.clickable {
				navigateToShow.invoke(tvShow)
			}
			.fillMaxWidth()
			.height(225.dp),
	) {
		ConstraintLayout {
			val (poster, title, date, overview) = createRefs()
			AsyncImage(
				model = ImageRequest.Builder(LocalContext.current)
					.data(tvShow.posterPath)
					.crossfade(true)
					.build(),
				placeholder = painterResource(R.drawable.placeholder_drawable),
				contentScale = ContentScale.Crop,
				contentDescription = null,
				modifier = Modifier
					.width(128.dp)
					.height(225.dp)
					.constrainAs(poster) {
						top.linkTo(parent.top)
						bottom.linkTo(parent.bottom)
						start.linkTo(parent.start)
					},
			)
			
			Text(
				overflow = TextOverflow.Ellipsis,
				maxLines = 2,
				modifier = Modifier
					.constrainAs(title) {
						top.linkTo(parent.top, margin = 16.dp)
						start.linkTo(poster.end, margin = 8.dp)
						end.linkTo(parent.end, margin = 16.dp)
						width = Dimension.fillToConstraints
						height = Dimension.preferredWrapContent
					},
				textAlign = TextAlign.Start,
				style = MaterialTheme.typography.titleSmall,
				text = tvShow.name,
			)
			
			Text(
				text = tvShow.lastAirDate,
				modifier = Modifier
					.constrainAs(date) {
						top.linkTo(title.bottom, margin = 4.dp)
						start.linkTo(poster.end, margin = 8.dp)
						end.linkTo(parent.end, margin = 16.dp)
						width = Dimension.fillToConstraints
						height = Dimension.preferredWrapContent
					},
				style = MaterialTheme.typography.labelSmall,
			)
			
			Text(
				text = tvShow.overview,
				overflow = TextOverflow.Ellipsis,
				maxLines = 6,
				textAlign = TextAlign.Start,
				modifier = Modifier
					.constrainAs(overview) {
						top.linkTo(date.bottom, margin = 8.dp)
						start.linkTo(poster.end, margin = 8.dp)
						end.linkTo(parent.end, margin = 16.dp)
						bottom.linkTo(poster.bottom)
						width = Dimension.preferredWrapContent
						height = Dimension.preferredWrapContent
					},
				style = MaterialTheme.typography.bodyMedium,
			)
		}
	}
}