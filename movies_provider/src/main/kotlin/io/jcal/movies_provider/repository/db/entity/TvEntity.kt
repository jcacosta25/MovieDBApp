package io.jcal.movies_provider.repository.db.entity

import androidx.annotation.NonNull
import androidx.room.*
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
    @ColumnInfo(name = DatabaseConstants.COLUMN_EPISODE_RUN_TIME)   
	   val episodeRunTime: List<Int>,
    @ColumnInfo(name = DatabaseConstants.COLUMN_FIRST_AIR_DATE)   
	   val firstAirDate: String,
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

data class TvShowSeasons(
    @Embedded   
	   val tvShowEntity: TvShowEntity,
    @Relation(   
		   parentColumn = DatabaseConstants.COLUMN_ID,   
		   entityColumn = DatabaseConstants.COLUMN_SHOW_ID   
	   )   
	   val seasons: List<SeasonEntity>
)

@Entity(
	tableName = DatabaseConstants.TABLE_SEASON,
	foreignKeys = [
		ForeignKey(
			entity = TvShowEntity::class,
			parentColumns = arrayOf(DatabaseConstants.COLUMN_ID),
			childColumns = arrayOf(DatabaseConstants.COLUMN_SHOW_ID)
		)
	],
	indices = [
		Index(DatabaseConstants.COLUMN_SHOW_ID)
	]
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
    @ColumnInfo(name = DatabaseConstants.COLUMN_SHOW_ID)   
	   val showId: Int
)

data class SeasonEpisodes(
    @Embedded   
	   val season: SeasonEntity,
    @Relation(   
		   parentColumn = DatabaseConstants.COLUMN_ID,   
		   entityColumn = DatabaseConstants.COLUMN_SEASON_ID   
	   )   
	   val episodes: List<EpisodeEntity>
)

@Entity(
	tableName = DatabaseConstants.TABLE_EPISODE,
	foreignKeys = [
		ForeignKey(
			entity = SeasonEntity::class,
			parentColumns = arrayOf(DatabaseConstants.COLUMN_ID),
			childColumns = arrayOf(DatabaseConstants.COLUMN_SEASON_ID)
		)
	],
	indices = [
		Index(DatabaseConstants.COLUMN_SEASON_ID)
	]
)
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
	   val voteCount: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_SEASON_ID)   
	   val seasonId: Int
)
