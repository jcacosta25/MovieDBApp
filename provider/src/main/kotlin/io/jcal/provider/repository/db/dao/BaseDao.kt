package io.jcal.provider.repository.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	fun insert(entity: T): Long

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertCoroutines(entity: T)

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	fun insertAll(entities: List<T>): List<Long>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAllCoroutines(entities: List<T>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun upsert(entity: T): Long

	@Update(onConflict = OnConflictStrategy.REPLACE)
	fun update(entity: T): Int

	@Delete
	fun delete(entity: T)

	@Delete
	suspend fun deleteCoroutines(entity: T)
}
