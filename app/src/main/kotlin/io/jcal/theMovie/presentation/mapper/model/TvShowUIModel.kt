package io.jcal.theMovie.presentation.mapper.model

import android.os.Parcel
import android.os.Parcelable
import io.jcal.movies_provider.repository.mapper.model.BaseModel

data class TvShowUIList(
    val dates: DatesUIModel,
    val page: Int,
    val results: List<TvShowUIModel>,
    val totalPages: Int,
    val totalResult: Int
) : BaseUIModel(), Parcelable {
    constructor(source: Parcel) : this(
        source.readParcelable<DatesUIModel>(DatesUIModel::class.java.classLoader)!!,
        source.readInt(),
        source.createTypedArrayList(TvShowUIModel.CREATOR)!!,
        source.readInt(),
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeParcelable(dates, 0)
        writeInt(page)
        writeTypedList(results)
        writeInt(totalPages)
        writeInt(totalResult)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<TvShowUIList> = object : Parcelable.Creator<TvShowUIList> {
            override fun createFromParcel(source: Parcel): TvShowUIList = TvShowUIList(source)
            override fun newArray(size: Int): Array<TvShowUIList?> = arrayOfNulls(size)
        }
    }
}

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
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString()!!,
        source.readInt(),
        source.readInt(),
        source.readString()!!,
        ArrayList<Int>().apply { source.readList(this, Int::class.java.classLoader) },
        source.readString()!!,
        source.readString()!!,
        1 == source.readInt(),
        source.createStringArrayList()!!,
        source.readString()!!,
        source.readInt(),
        source.readString()!!,
        source.readInt(),
        source.readInt(),
        source.readInt(),
        source.createStringArrayList()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readDouble(),
        source.readString()!!,
        source.createTypedArrayList(SeasonUIModel.CREATOR)!!,
        source.readString()!!,
        source.readString()!!,
        source.readDouble(),
        source.readInt(),
        ArrayList<Int>().apply { source.readList(this, Int::class.java.classLoader) }
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(airDate)
        writeInt(episodeCount)
        writeInt(seasonNumber)
        writeString(backdropPath)
        writeList(episodeRunTime)
        writeString(firstAirDate)
        writeString(homepage)
        writeInt((if (inProduction) 1 else 0))
        writeStringList(languages)
        writeString(lastAirDate)
        writeInt(lastEpisodeToAir)
        writeString(name)
        writeInt(nextEpisodeToAir)
        writeInt(numberOfEpisodes)
        writeInt(numberOfSeasons)
        writeStringList(originCountry)
        writeString(originalLanguage)
        writeString(originalName)
        writeString(overview)
        writeDouble(popularity)
        writeString(posterPath)
        writeTypedList(seasons)
        writeString(status)
        writeString(type)
        writeDouble(voteAverage)
        writeInt(voteCount)
        writeList(genreIds)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<TvShowUIModel> =
            object : Parcelable.Creator<TvShowUIModel> {
                override fun createFromParcel(source: Parcel): TvShowUIModel = TvShowUIModel(source)
                override fun newArray(size: Int): Array<TvShowUIModel?> = arrayOfNulls(size)
            }
    }
}

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
) : BaseModel(), Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString()!!,
        source.readInt(),
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readInt(),
        source.createTypedArrayList(EpisodeUIModel.CREATOR)!!,
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(airDate)
        writeInt(episodeCount)
        writeString(name)
        writeString(overview)
        writeString(posterPath)
        writeInt(seasonNumber)
        writeTypedList(episodes)
        writeInt(showId)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SeasonUIModel> =
            object : Parcelable.Creator<SeasonUIModel> {
                override fun createFromParcel(source: Parcel): SeasonUIModel = SeasonUIModel(source)
                override fun newArray(size: Int): Array<SeasonUIModel?> = arrayOfNulls(size)
            }
    }
}


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
) : BaseUIModel(), Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString()!!,
        source.readInt(),
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readInt(),
        source.readInt(),
        source.readString()!!,
        source.readInt(),
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(airDate)
        writeInt(episodeNumber)
        writeString(name)
        writeString(overview)
        writeString(productionCode)
        writeInt(seasonNumber)
        writeInt(showId)
        writeString(stillPath)
        writeInt(voteAverage)
        writeInt(voteCount)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<EpisodeUIModel> =
            object : Parcelable.Creator<EpisodeUIModel> {
                override fun createFromParcel(source: Parcel): EpisodeUIModel =
                    EpisodeUIModel(source)

                override fun newArray(size: Int): Array<EpisodeUIModel?> = arrayOfNulls(size)
            }
    }
}
