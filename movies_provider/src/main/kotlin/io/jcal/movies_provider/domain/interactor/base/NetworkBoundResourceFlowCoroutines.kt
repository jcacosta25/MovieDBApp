package io.jcal.movies_provider.domain.interactor.base

import io.jcal.movies_provider.repository.mapper.model.BaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class NetworkBoundResourceFlowCoroutines<ModelType : BaseModel, T> {

    fun execute(params: T): Flow<ModelType> = flow {
        emit(getLoadingObject())
        val dbSource = loadFromDb(params)
        if (shouldFetch(dbSource)) {
            val cloudResult = createCall(params)
            saveCallResult(cloudResult)
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

    private fun shouldFetch(
        data: ModelType?,
        time: Long = forecastCacheThresholdMillis
    ): Boolean = time == forecastCacheThresholdMillis

    protected fun shouldLoad(params: T, data: ModelType): Boolean = true

    protected abstract suspend fun loadFromDb(params: T): ModelType

    protected abstract suspend fun createCall(params: T): ModelType

    protected abstract fun getLoadingObject(): ModelType

    companion object {
        private const val forecastCacheThresholdMillis = 3 * 3600000L // 3 hours//
    }
}
