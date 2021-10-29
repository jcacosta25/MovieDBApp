package io.jcal.movies_provider.domain.interactor.base

import io.jcal.movies_provider.repository.mapper.model.BaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class NetworkBoundResource<ModelType : BaseModel, T> {
	
	fun execute(params: T = parameters): Flow<ModelType> = flow {
		emit(getLoadingObject())
		val dbSource = loadFromDb(params)
		if (shouldFetch(dbSource)) {
			val cloudResult = createCall(params)
			cloudResult.takeIf { it.isSuccess }?.let { saveCallResult(it) }
			if (shouldLoad(params, cloudResult)) {
				emit(loadFromDb(params))
			} else {
				emit(cloudResult)
			}
		} else {
			emit(dbSource)
		}
	}
	
	protected abstract suspend fun saveCallResult(item: ModelType)
	
	protected open fun shouldFetch(
		data: ModelType?,
		time: Long = forecastCacheThresholdMillis
	): Boolean = data?.let { true }.let { it ?: (time == forecastCacheThresholdMillis) }
	
	protected open fun shouldLoad(params: T, data: ModelType): Boolean = false
	
	protected abstract suspend fun loadFromDb(params: T): ModelType
	
	protected abstract suspend fun createCall(params: T): ModelType
	
	protected abstract fun getLoadingObject(): ModelType
	
	protected abstract val parameters: T
	
	companion object {
		private const val forecastCacheThresholdMillis = 3 * 3600000L // 3 hours//
	}
}
