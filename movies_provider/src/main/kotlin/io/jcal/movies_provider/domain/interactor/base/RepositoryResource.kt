package io.jcal.movies_provider.domain.interactor.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import io.jcal.movies_provider.repository.mapper.model.BaseModel
import kotlinx.coroutines.Dispatchers

abstract class RepositoryResource<ModelType : BaseModel, T> {

    fun execute(params: T): LiveData<ModelType> =
        liveData(Dispatchers.IO) {
            emit(getLoadingObject())
            val dbSource = loadFromDb(params)
            if (shouldFetch(dbSource.value)) {
                val cloudSource = createCall(params)
                saveCallResult(cloudSource)
                if (shouldLoad(params, cloudSource)) {
                    emitSource(loadFromDb(params))
                } else {
                    emit(cloudSource)
                }
            } else {
                emitSource(dbSource)
            }
        }


    protected abstract fun saveCallResult(item: ModelType)

    protected fun shouldFetch(data: ModelType?): Boolean = true

    protected fun shouldLoad(params: T, data: ModelType): Boolean = true

    protected abstract fun loadFromDb(params: T): LiveData<ModelType>

    protected abstract suspend fun createCall(params: T): ModelType

    protected abstract fun getLoadingObject(): ModelType
}