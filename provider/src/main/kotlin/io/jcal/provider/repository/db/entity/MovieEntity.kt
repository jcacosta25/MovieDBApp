package io.jcal.provider.repository.db.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.jcal.provider.repository.db.DatabaseConstants

@Entity(tableName = DatabaseConstants.TABLE_MOVIE)
data class MovieEntity(
    @NonNull   
	   @PrimaryKey   
	   @ColumnInfo(name = DatabaseConstants.COLUMN_ID)   
	   val id: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_ADULT)   
	   val adult: Boolean,
    @ColumnInfo(name = DatabaseConstants.COLUMN_BACKDROP)   
	   val backdropPath: String,
//    @ColumnInfo(name = DatabaseConstants.COLUMN_COLLECTION)
//    val belongsToCollection: Any,
    @ColumnInfo(name = DatabaseConstants.COLUMN_BUDGET)   
	   val budget: Int,
//    @ColumnInfo(name = DatabaseConstants.COLUMN_GENRES)
//    val genres: List<GenreEntity>,
    @ColumnInfo(name = DatabaseConstants.COLUMN_GENRES_ID)   
	   val genreIds: List<Int>,
    @ColumnInfo(name = DatabaseConstants.COLUMN_HOMEPAGE)   
	   val homepage: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_IMDB)   
	   val imdbId: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_ORIGINAL_LANGUAGE)   
	   val originalLanguage: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_ORIGINAL_TITLE)   
	   val originalTitle: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_OVERVIEW)   
	   val overview: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_POPULARITY)   
	   val popularity: Double,
    @ColumnInfo(name = DatabaseConstants.COLUMN_POSTER)   
	   val posterPath: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_RELEASE_DATE)   
	   val releaseDate: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_REVENUE)   
	   val revenue: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_RUNTIME)   
	   val runtime: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_SPOKEN_LANGUAGES)   
	   val spokenLanguageModels: List<String>,
    @ColumnInfo(name = DatabaseConstants.COLUMN_STATUS)   
	   val status: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_TAGLINE)   
	   val tagline: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_TITLE)   
	   val title: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_VIDEO)   
	   val video: Boolean,
    @ColumnInfo(name = DatabaseConstants.COLUMN_VOTE_AVERAGE)   
	   val voteAverage: Double,
    @ColumnInfo(name = DatabaseConstants.COLUMN_VOTE_COUNT)   
	   val voteCount: Int
)
