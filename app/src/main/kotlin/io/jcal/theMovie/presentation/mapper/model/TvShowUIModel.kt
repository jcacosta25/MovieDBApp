package io.jcal.theMovie.presentation.mapper.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import io.jcal.provider.repository.mapper.model.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowUIList(
    val dates: DatesUIModel,
    val page: Int,
    val results: List<TvShowUIModel>,
    val totalPages: Int,
    val totalResult: Int
) : BaseUIModel(), Parcelable

@Parcelize
data class TvShowUIModel(
    val id: Int = 0,
    val airDate: String = "",
    val episodeCount: Int = 0,
    val seasonNumber: Int = 0,
    val backdropPath: String = "",
    val episodeRunTime: List<Int> = emptyList(),
    val firstAirDate: String = "",
    val homepage: String = "",
    val inProduction: Boolean = false,
    val languages: List<String> = emptyList(),
    val lastAirDate: String = "",
    val lastEpisodeToAir: Int = 0,
    val name: String = "",
    val nextEpisodeToAir: Int = 0,
    val numberOfEpisodes: Int = 0,
    val numberOfSeasons: Int = 0,
    val originCountry: List<String> = emptyList(),
    val originalLanguage: String = "",
    val originalName: String = "",
    val overview: String = "",
    val popularity: Double = EMPTY_DOUBLE,
    val posterPath: String = "",
    val seasons: List<SeasonUIModel> = emptyList(),
    val status: String = "",
    val type: String = "",
    val voteAverage: Double = EMPTY_DOUBLE,
    val voteCount: Int = 0,
    val genreIds: List<Int> = emptyList()
) : BaseUIModel(), Parcelable {
	companion object {
		
		val SHOW_DIF = object : DiffUtil.ItemCallback<TvShowUIModel>() {
			override fun areItemsTheSame(oldItem: TvShowUIModel, newItem: TvShowUIModel): Boolean =
				oldItem.id == newItem.id
			
			override fun areContentsTheSame(
			    oldItem: TvShowUIModel,
			    newItem: TvShowUIModel
			): Boolean =
				oldItem == newItem
		}
	}
}

@Parcelize
data class SeasonUIModel(
    val id: Int = 0,
    val airDate: String = "",
    val episodeCount: Int = 0,
    val name: String = "",
    val overview: String = "",
    val posterPath: String = "",
    val seasonNumber: Int = 0,
    val episodes: List<EpisodeUIModel> = emptyList(),
    val showId: Int = 0
) : BaseModel(), Parcelable

@Parcelize
data class EpisodeUIModel(
    val id: Int = 0,
    val airDate: String = "",
    val episodeNumber: Int = 0,
    val name: String = "",
    val overview: String = "",
    val productionCode: String = "",
    val seasonNumber: Int = 0,
    val showId: Int = 0,
    val stillPath: String = "",
    val voteAverage: Int = 0,
    val voteCount: Int = 0
) : BaseUIModel(), Parcelable
