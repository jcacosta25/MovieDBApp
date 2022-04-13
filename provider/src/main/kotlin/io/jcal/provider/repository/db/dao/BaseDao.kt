package io.jcal.provider.repository.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Update

interface BaseDao<T> {

	@Insert(onConflict = IGNORE)
	fun insert(entity: T): Long

	@Insert(onConflict = REPLACE)
	suspend fun insertCoroutines(entity: T)

	@Insert(onConflict = IGNORE)
	fun insertAll(entities: List<T>): List<Long>

	@Insert(onConflict = REPLACE)
	suspend fun insertAllCoroutines(entities: List<T>)

	@Insert(onConflict = REPLACE)
	fun upsert(entity: T): Long

	@Update(onConflict = REPLACE)
	fun update(entity: T): Int

	@Delete
	fun delete(entity: T)

	@Delete
	suspend fun deleteCoroutines(entity: T)
}
