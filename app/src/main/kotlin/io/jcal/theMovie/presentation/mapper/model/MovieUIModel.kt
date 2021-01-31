package io.jcal.theMovie.presentation.mapper.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieUIModel(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val budget: Int,
    val genreIds: List<Int>,
    val homepage: String,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double = EMPTY_DOUBLE,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguageModels: List<String>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean = false,
    val voteAverage: Double = EMPTY_DOUBLE,
    val voteCount: Int
) : BaseUIModel(), Parcelable {

    companion object {

        val MOVIE_DIF = object : DiffUtil.ItemCallback<MovieUIModel>() {
            override fun areItemsTheSame(oldItem: MovieUIModel, newItem: MovieUIModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieUIModel, newItem: MovieUIModel): Boolean =
                oldItem == newItem
        }
    }
}

@Parcelize
data class MovieUIModelList(
    val dates: DatesUIModel,
    val page: Int,
    val results: List<MovieUIModel>,
    val totalPages: Int,
    val totalResult: Int
) : BaseUIModel(), Parcelable

@Parcelize
data class DatesUIModel(
    val maximum: String,
    val minimum: String
) : BaseUIModel()
