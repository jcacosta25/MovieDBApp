package io.jcal.movies_provider.repository.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import io.jcal.movies_provider.repository.db.DatabaseConstants
import io.jcal.movies_provider.repository.db.entity.TvShowEntity

@Dao
interface TvShowDao : BaseDao<TvShowEntity> {

    @Query("select * from ${DatabaseConstants.TABLE_TV_SHOW} where ${DatabaseConstants.COLUMN_TITLE} = :showTitle")
    fun findShowByTitle(showTitle: String): LiveData<TvShowEntity>

    @Query("select * from ${DatabaseConstants.TABLE_TV_SHOW}")
    fun getAllShows(): LiveData<List<TvShowEntity>>
}