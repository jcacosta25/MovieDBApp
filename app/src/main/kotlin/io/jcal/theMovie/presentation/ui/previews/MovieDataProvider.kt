package io.jcal.theMovie.presentation.ui.previews

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import io.jcal.theMovie.presentation.mapper.model.MovieUIModel

class MovieDataProvider : PreviewParameterProvider<MovieUIModel> {
	override val values: Sequence<MovieUIModel>
		get() = sequenceOf(
			MovieUIModel(
				title = "Fight Club",
				releaseDate = "1999-10-12",
				status = "Released",
				voteAverage = 7.8,
				voteCount = 3439,
				adult = false,
				homepage = "",
				revenue = 100853753,
				imdbId = "tt0137523",
				posterPath = "",
				backdropPath = "/fCayJrkfRaCRCTh8GqN30f8oyQF.jpg",
				id = 550,
				popularity = 0.5,
				budget = 63000000,
				runtime = 139,
				originalLanguage = "en",
				video = false,
				tagline = "How much can you know about yourself if you've never been in a fight?",
				spokenLanguageModels = listOf("English"),
				overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
				originalTitle = "The Fight Club"
			)
		)
}