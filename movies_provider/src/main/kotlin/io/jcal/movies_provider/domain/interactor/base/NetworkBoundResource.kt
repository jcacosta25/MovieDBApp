package io.jcal.movies_provider.domain.interactor.base

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import io.jcal.movies_provider.domain.executors.AppExecutors
import io.jcal.movies_provider.repository.mapper.model.BaseModel


/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 *
 *
 * You can read more about it in the [Architecture
 * Guide](https://developer.android.com/arch).
 * @param <ResultType>
</RequestType></ResultType> */
abstract class NetworkBoundResource<ModelType : BaseModel, T>
@MainThread
internal constructor(private val appExecutors: AppExecutors) {

    private lateinit var result: MediatorLiveData<Resource<ModelType>>

    fun execute(params: T): LiveData<Resource<ModelType>> {
        result = MediatorLiveData()
        result.postValue(Resource.loading(getLoadingObject()))
        val dbSource = loadFromDb(params)
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource, params)
            } else {
                result.addSource(dbSource) {
                    setValue(
                        if (it.error) Resource.error(it.errorCode, it)
                        else Resource.success(it)
                    )
                }
            }
        }
        return result
    }

    @MainThread
    private fun setValue(value: Resource<ModelType>) {
        if (result.value != value) {
            result.value = value
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ModelType>, params: T) {
        val apiResponse = createCall(params)
        result.addSource(dbSource) {
            setValue(
                Resource.loading(it)
            )
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            if (response != null && !response.error) {
                appExecutors.getDiskThread().execute {
                    saveCallResult(processResponse(response))
                    appExecutors.getMainThread().execute {
                        result.addSource(loadFromDb(params)) {
                            setValue(
                                Resource.success(it)
                            )
                        }
                    }
                }
            } else {
                result.addSource(
                    dbSource
                ) {
                    setValue(
                        Resource.error(
                            response.errorCode,
                            it
                        )
                    )
                }
            }
        }
    }

    protected fun onFetchFailed() {}

    @WorkerThread
    protected open fun processResponse(response: ModelType): ModelType = response

    @WorkerThread
    protected abstract fun saveCallResult(item: ModelType)

    @MainThread
    protected abstract fun shouldFetch(data: ModelType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(params: T): LiveData<ModelType>

    @MainThread
    protected abstract fun createCall(params: T): LiveData<ModelType>

    protected abstract fun getLoadingObject(): ModelType
}