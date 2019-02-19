package io.jcal.movies_provider.repository.db.dao

import androidx.lifecycle.LiveData
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
    fun findShow(showId: Int): LiveData<TvShowSeasons>

    @Query("select * from ${DatabaseConstants.TABLE_SEASON} where ${DatabaseConstants.COLUMN_SHOW_ID} = :showId")
    fun findShowSeasons(showId: Int): LiveData<SeasonEntity>

    @Transaction
    @Query("select * from ${DatabaseConstants.TABLE_TV_SHOW}")
    fun getAllShows(): LiveData<List<TvShowSeasons>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSeasons(entities: List<SeasonEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSeasonEntity(entity: SeasonEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEpisodes(entity: List<EpisodeEntity>): List<Long>

    @Transaction
    fun insertSeason(episodes: SeasonEpisodes): Long {
        insertEpisodes(episodes.episodes)
        return insertSeasonEntity(episodes.season)
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertShows(entities: List<TvShowEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertShow(entity: TvShowEntity): Long

    @Transaction
    fun insert(show: TvShowSeasons): Long {
        show.seasons.map { insertSeasonEntity(it) }
        return insertShow(show.tvShowEntity)
    }

    @Transaction
    fun insertAll(shows: List<TvShowSeasons>): List<Long> {
        return shows.map { show ->
            show.seasons.map { insertSeasonEntity(it) }
            insertShow(show.tvShowEntity)
        }
    }

}