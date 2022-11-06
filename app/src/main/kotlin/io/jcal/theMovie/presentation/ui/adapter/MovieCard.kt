package io.jcal.theMovie.presentation.ui.adapter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
import io.jcal.theMovie.presentation.mapper.model.MovieUIModel
import io.jcal.theMovie.presentation.ui.previews.MovieDataProvider

@Preview
@Composable
fun MovieCard(
    @PreviewParameter(MovieDataProvider::class)
    movie: MovieUIModel,
    navigateToMovie: (MovieUIModel) -> Unit = { _ -> }
) {
	val colorAccent = colorResource(R.color.colorAccent)
	MaterialTheme {
		Card(
			modifier = Modifier
				.clickable {
					navigateToMovie.invoke(movie)
				}
				.fillMaxWidth()
				.height(225.dp)
		) {
			ConstraintLayout {
				val (poster, title, rating, date, overview) = createRefs()
				AsyncImage(
					model = ImageRequest.Builder(LocalContext.current)
						.data(movie.posterPath)
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
						}
				)
				
				Text(
					overflow = TextOverflow.Ellipsis,
					maxLines = 2,
					modifier = Modifier
						.constrainAs(title) {
							top.linkTo(parent.top, margin = 16.dp)
							start.linkTo(poster.end, margin = 8.dp)
							end.linkTo(rating.start, margin = 8.dp)
							width = Dimension.fillToConstraints
							height = Dimension.preferredWrapContent
						},
					textAlign = TextAlign.Start,
					style = MaterialTheme.typography.h6,
					text = movie.title
				)
				
				Text(
                    text = movie.voteAverage.toString(),
					style = MaterialTheme.typography.body1,
					fontWeight = FontWeight.Bold,
					textAlign = TextAlign.Center,
					modifier = Modifier
						.padding(8.dp)
						.drawBehind {
							drawCircle(
								color = colorAccent,
								radius = this.size.maxDimension
							)
						}
						.constrainAs(rating) {
							top.linkTo(parent.top, margin = 16.dp)
							end.linkTo(parent.end, margin = 16.dp)
							width = Dimension.fillToConstraints
						}
                )
				
				Text(
					text = movie.releaseDate,
                    modifier = Modifier
						.constrainAs(date) {
							top.linkTo(title.bottom, margin = 4.dp)
							start.linkTo(poster.end, margin = 8.dp)
							end.linkTo(rating.end, margin = 16.dp)
							width = Dimension.fillToConstraints
							height = Dimension.preferredWrapContent
						},
					style = MaterialTheme.typography.caption
				)
				
				Text(
					text = movie.overview,
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
					style = MaterialTheme.typography.subtitle1
				)
			}
		}
	}
}