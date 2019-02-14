package io.jcal.theMovie.presentation.mapper.model

import android.os.Parcel
import android.os.Parcelable

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
    constructor(source: Parcel) : this(
        source.readInt(),
        1 == source.readInt(),
        source.readString(),
        source.readInt(),
        ArrayList<Int>().apply { source.readList(this, Int::class.java.classLoader) },
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readDouble(),
        source.readString(),
        source.readString(),
        source.readInt(),
        source.readInt(),
        source.createStringArrayList(),
        source.readString(),
        source.readString(),
        source.readString(),
        1 == source.readInt(),
        source.readDouble(),
        source.readInt()
    )

    override fun describeContents() = EMPTY_INT

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeInt((if (adult) 1 else EMPTY_INT))
        writeString(backdropPath)
        writeInt(budget)
        writeList(genreIds)
        writeString(homepage)
        writeString(imdbId)
        writeString(originalLanguage)
        writeString(originalTitle)
        writeString(overview)
        writeDouble(popularity)
        writeString(posterPath)
        writeString(releaseDate)
        writeInt(revenue)
        writeInt(runtime)
        writeStringList(spokenLanguageModels)
        writeString(status)
        writeString(tagline)
        writeString(title)
        writeInt((if (video) 1 else EMPTY_INT))
        writeDouble(voteAverage)
        writeInt(voteCount)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MovieUIModel> = object : Parcelable.Creator<MovieUIModel> {
            override fun createFromParcel(source: Parcel): MovieUIModel = MovieUIModel(source)
            override fun newArray(size: Int): Array<MovieUIModel?> = arrayOfNulls(size)
        }
    }
}

data class MovieUIModelList(
    val dates: DatesUIModel,
    val page: Int,
    val results: List<MovieUIModel>,
    val totalPages: Int,
    val totalResult: Int
) : BaseUIModel(), Parcelable {
    constructor(source: Parcel) : this(
        source.readParcelable<DatesUIModel>(DatesUIModel::class.java.classLoader),
        source.readInt(),
        source.createTypedArrayList(MovieUIModel.CREATOR),
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
        val CREATOR: Parcelable.Creator<MovieUIModelList> =
            object : Parcelable.Creator<MovieUIModelList> {
                override fun createFromParcel(source: Parcel): MovieUIModelList =
                    MovieUIModelList(source)

                override fun newArray(size: Int): Array<MovieUIModelList?> = arrayOfNulls(size)
            }
    }
}

data class DatesUIModel(
    val maximum: String,
    val minimum: String
) : BaseUIModel(), Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString()
    )

    override fun describeContents() = EMPTY_INT

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(maximum)
        writeString(minimum)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DatesUIModel> = object : Parcelable.Creator<DatesUIModel> {
            override fun createFromParcel(source: Parcel): DatesUIModel = DatesUIModel(source)
            override fun newArray(size: Int): Array<DatesUIModel?> = arrayOfNulls(size)
        }
    }
}
