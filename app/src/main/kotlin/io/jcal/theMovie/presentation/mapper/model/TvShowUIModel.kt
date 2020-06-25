package io.jcal.theMovie.presentation.mapper.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import io.jcal.movies_provider.repository.mapper.model.BaseModel
import kotlinx.android.parcel.Parcelize

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
    val id: Int,
    val airDate: String,
    val episodeCount: Int,
    val seasonNumber: Int,
    val backdropPath: String,
    val episodeRunTime: List<Int>,
    val firstAirDate: String,
    val homepage: String,
    val inProduction: Boolean = false,
    val languages: List<String>,
    val lastAirDate: String,
    val lastEpisodeToAir: Int,
    val name: String,
    val nextEpisodeToAir: Int,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val seasons: List<SeasonUIModel>,
    val status: String,
    val type: String,
    val voteAverage: Double,
    val voteCount: Int,
    val genreIds: List<Int>
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
    val id: Int,
    val airDate: String,
    val episodeCount: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val seasonNumber: Int,
    val episodes: List<EpisodeUIModel>,
    val showId: Int
) : BaseModel(), Parcelable

@Parcelize
data class EpisodeUIModel(
    val id: Int,
    val airDate: String,
    val episodeNumber: Int,
    val name: String,
    val overview: String,
    val productionCode: String,
    val seasonNumber: Int,
    val showId: Int,
    val stillPath: String,
    val voteAverage: Int,
    val voteCount: Int
) : BaseUIModel(), Parcelable
