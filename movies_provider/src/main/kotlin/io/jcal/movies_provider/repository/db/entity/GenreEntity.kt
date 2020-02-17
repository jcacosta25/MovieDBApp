package io.jcal.movies_provider.repository.db.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.jcal.movies_provider.repository.db.DatabaseConstants

@Entity(tableName = DatabaseConstants.TABLE_GENRE)
data class GenreEntity(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = DatabaseConstants.COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = DatabaseConstants.COLUMN_NAME)
    val name: String
)
