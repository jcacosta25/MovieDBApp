package io.jcal.movies_provider.repository.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.jcal.movies_provider.repository.db.DatabaseConstants
import io.jcal.movies_provider.repository.db.entity.EpisodeEntity
import io.jcal.movies_provider.repository.db.entity.SeasonEntity
import io.jcal.movies_provider.repository.db.entity.SeasonEpisodes
import io.jcal.movies_provider.repository.db.entity.TvShowEntity
import io.jcal.movies_provider.repository.db.entity.TvShowSeasons

@Dao
interface TvShowDao {

    @Transaction
    @Query("select * from ${DatabaseConstants.TABLE_TV_SHOW} where ${DatabaseConstants.COLUMN_ID} = :showId")
    suspend fun findShow(showId: Int): TvShowSeasons?

    @Query("select * from ${DatabaseConstants.TABLE_SEASON} where ${DatabaseConstants.COLUMN_SHOW_ID} = :showId")
    suspend fun findShowSeasons(showId: Int): List<SeasonEntity>

    @Transaction
    @Query("select * from ${DatabaseConstants.TABLE_TV_SHOW}")
    suspend fun getAllShows(): List<TvShowSeasons>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSeasons(entities: List<SeasonEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSeasonEntity(entity: SeasonEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEpisodes(entity: List<EpisodeEntity>): List<Long>

    @Transaction
    suspend fun insertSeason(episodes: SeasonEpisodes): Long {
        insertEpisodes(episodes.episodes)
        return insertSeasonEntity(episodes.season)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShows(entities: List<TvShowEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShow(entity: TvShowEntity): Long

    @Transaction
    suspend fun insert(show: TvShowSeasons): Long {
        show.seasons.map { insertSeasonEntity(it) }
        return insertShow(show.tvShowEntity)
    }

    @Transaction
    suspend fun insertAll(shows: List<TvShowSeasons>): List<Long> {
        return shows.map { show ->
            show.seasons.map { insertSeasonEntity(it) }
            insertShow(show.tvShowEntity)
        }
    }
}
