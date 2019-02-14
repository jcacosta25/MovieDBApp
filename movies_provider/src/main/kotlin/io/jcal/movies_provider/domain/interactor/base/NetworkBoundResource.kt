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
abstract class NetworkBoundResource<ModelType : BaseModel>
@MainThread
internal constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ModelType>>()

    fun init(loading: ModelType) {
        result.value =
            Resource.loading(loading)
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            val should = shouldFetch(data)
            if (should) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) {
                    setValue(
                        Resource.success(
                            it!!
                        )
                    )
                }
            }
        }
    }


    @MainThread
    private fun setValue(value: Resource<ModelType>) {
        if (result.value != value) {
            result.value = value
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ModelType>) {
        val apiResponse = createCall()
        result.addSource(dbSource) {
            setValue(
                Resource.loading(
                    it!!
                )
            )
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            if (response != null && !response.error) {
                appExecutors.getDiskThread().execute {
                    saveCallResult(processResponse(response))
                    appExecutors.getMainThread().execute {
                        result.addSource(loadFromDb()) {
                            setValue(
                                Resource.success(
                                    it!!
                                )
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
                            response!!.errorCode.toString(),
                            it!!
                        )
                    )
                }
            }
        }
    }

    protected fun onFetchFailed() {}

    fun asLiveData(): LiveData<Resource<ModelType>> = result

    @WorkerThread
    protected open fun processResponse(response: ModelType): ModelType = response

    @WorkerThread
    protected abstract fun saveCallResult(item: ModelType)

    @MainThread
    protected abstract fun shouldFetch(data: ModelType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ModelType>

    @MainThread
    protected abstract fun createCall(): LiveData<ModelType>

}