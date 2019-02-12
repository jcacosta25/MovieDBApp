package io.jcal.movies_provider.repository.db.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import io.jcal.movies_provider.repository.db.DatabaseConstants


@Entity(tableName = DatabaseConstants.TABLE_TV_SHOW)
data class TvShowEntity(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = DatabaseConstants.COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_AIR_DATE)
    val airDate: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_EPISODE_COUNT)
    val episodeCount: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_SEASON_NUMBER)
    val seasonNumber: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_BACKDROP)
    val backdropPath: String,
//    @ColumnInfo(name = DatabaseConstants.COLUMN_CREATED_BY)
//    val createdBy: List<CreatorEntity>,
    @ColumnInfo(name = DatabaseConstants.COLUMN_EPISODE_RUN_TIME)
    val episodeRunTime: List<Int>,
    @ColumnInfo(name = DatabaseConstants.COLUMN_FIRST_AIR_DATE)
    val firstAirDate: String,
//    @ColumnInfo(name = DatabaseConstants.COLUMN_GENRES)
//    val genres: List<GenreEntity>,
    @ColumnInfo(name = DatabaseConstants.COLUMN_HOMEPAGE)
    val homepage: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_IN_PRODUCTION)
    val inProduction: Boolean,
    @ColumnInfo(name = DatabaseConstants.COLUMN_LANGUAGES)
    val languages: List<String>,
    @ColumnInfo(name = DatabaseConstants.COLUMN_LAST_AIR_DATE)
    val lastAirDate: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_LAST_EPISODE_TO_AIR)
    val lastEpisodeToAir: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_NAME)
    val name: String,
//    @ColumnInfo(name = DatabaseConstants.COLUMN_NETWORKS)
//    val networks: List<TvNetworkEntity>,
    @ColumnInfo(name = DatabaseConstants.COLUMN_NEXT_EPISODE_TO_AIR)
    val nextEpisodeToAir: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_NUMBER_OF_EPISODES)
    val numberOfEpisodes: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_NUMBER_OF_SEASONS)
    val numberOfSeasons: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_ORIGIN_COUNTRY)
    val originCountry: List<String>,
    @ColumnInfo(name = DatabaseConstants.COLUMN_ORIGINAL_LANGUAGE)
    val originalLanguage: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_ORIGINAL_NAME)
    val originalName: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_OVERVIEW)
    val overview: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_POPULARITY)
    val popularity: Double,
    @ColumnInfo(name = DatabaseConstants.COLUMN_POSTER)
    val posterPath: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_SEASONS)
    val seasons: List<SeasonEntity>,
    @ColumnInfo(name = DatabaseConstants.COLUMN_STATUS)
    val status: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_TYPE)
    val type: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_VOTE_AVERAGE)
    val voteAverage: Double,
    @ColumnInfo(name = DatabaseConstants.COLUMN_VOTE_COUNT)
    val voteCount: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_GENRES_ID)
    val genreIds: List<Int>
)

@Entity(
    tableName = DatabaseConstants.TABLE_SEASON,
    foreignKeys = [ForeignKey(
        entity = TvShowEntity::class,
        parentColumns = arrayOf(DatabaseConstants.COLUMN_ID),
        childColumns = arrayOf(DatabaseConstants.COLUMN_SHOW_ID),
        onDelete = ForeignKey.CASCADE
    )]
)
data class SeasonEntity(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = DatabaseConstants.COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_AIR_DATE)
    val airDate: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_EPISODE_COUNT)
    val episodeCount: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_NAME)
    val name: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_OVERVIEW)
    val overview: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_POSTER)
    val posterPath: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_SEASON_NUMBER)
    val seasonNumber: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_EPISODES)
    val episodes: List<EpisodeEntity>,
    @ColumnInfo(name = DatabaseConstants.COLUMN_SHOW_ID)
    val showId: Int
)

@Entity(tableName = DatabaseConstants.TABLE_EPISODE,
    foreignKeys = [ForeignKey(
        entity = TvShowEntity::class,
        parentColumns = arrayOf(DatabaseConstants.COLUMN_ID),
        childColumns = arrayOf(DatabaseConstants.COLUMN_SHOW_ID),
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = SeasonEntity::class,
        parentColumns = arrayOf(DatabaseConstants.COLUMN_SEASON_NUMBER),
        childColumns = arrayOf(DatabaseConstants.COLUMN_SEASON_NUMBER)
    )])
data class EpisodeEntity(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = DatabaseConstants.COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_AIR_DATE)
    val airDate: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_EPISODE_NUMBER)
    val episodeNumber: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_NAME)
    val name: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_OVERVIEW)
    val overview: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_PRODUCTION_CODE)
    val productionCode: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_SEASON_NUMBER)
    val seasonNumber: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_SHOW_ID)
    val showId: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_STILL)
    val stillPath: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_VOTE_AVERAGE)
    val voteAverage: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_VOTE_COUNT)
    val voteCount: Int
//    @ColumnInfo(name = DatabaseConstants.COLUMN_CREW_MEMBERS)
//    val crew: List<CrewEntity>,
//    @ColumnInfo(name = DatabaseConstants.COLUMN_GUEST_STARS)
//    val guestStars: List<GuestEntity>
)

@Entity(tableName = DatabaseConstants.TABLE_TV_NETWORK)
data class TvNetworkEntity(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = DatabaseConstants.COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_LOGO)
    val logoPath: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_NAME)
    val name: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_ORIGIN_COUNTRY)
    val originCountry: String
)

@Entity(tableName = DatabaseConstants.TABLE_CREATOR)
data class CreatorEntity(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = DatabaseConstants.COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_CREDIT_ID)
    val creditId: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_GENDER)
    val gender: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_NAME)
    val name: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_PROFILE)
    val profilePath: String
)

@Entity(tableName = DatabaseConstants.TABLE_GUESTS)
data class GuestEntity(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = DatabaseConstants.COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_CREDIT_ID)
    val creditId: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_NAME)
    val name: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_PROFILE)
    val profilePath: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_CHARACTER)
    val character: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_ORDER)
    val order: Int
)

@Entity(tableName = DatabaseConstants.TABLE_CREW)
data class CrewEntity(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = DatabaseConstants.COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_CREDIT_ID)
    val creditId: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_DEPARTMENT)
    val department: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_JOB)
    val job: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_NAME)
    val name: String,
    @ColumnInfo(name = DatabaseConstants.COLUMN_PROFILE)
    val profilePath: String
)