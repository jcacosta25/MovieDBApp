package io.jcal.movies_provider.repository.api.network.liveDataAdapter

import androidx.lifecycle.LiveData
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class LiveDataCallAdapterFactory @Inject constructor() : CallAdapter.Factory() {
	
	override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
		if (getRawType(returnType) != LiveData::class.java) {
			return null
		}
		
		val observableType = getParameterUpperBound(
            FIRST_GENERIC_ARGUMENT,
            returnType as ParameterizedType
        )
		
		val rawObservableType = getRawType(observableType)
		require(rawObservableType == ApiResponse::class.java) { "Type must be an ApiResponse class" }
		
		require(observableType is ParameterizedType) { "ApiResponse must be parametrized" }
		
		val responseType = getParameterUpperBound(
            FIRST_GENERIC_ARGUMENT,
            observableType
        )
		
		val errorType = getParameterUpperBound(
            SECOND_GENERIC_ARGUMENT,
            observableType
        )
		
		return LiveDataCallAdapter<Any, Any>(
            responseType,
            errorType,
            annotations,
            retrofit
        )
	}
	
	companion object {
		fun create(): LiveDataCallAdapterFactory =
			LiveDataCallAdapterFactory()
		
		private const val FIRST_GENERIC_ARGUMENT = 0
		private const val SECOND_GENERIC_ARGUMENT = 1
	}
}

/**
 * A Retrofit adapter that converts the Call into a LiveData of ApiResponse.
 *
 * @param <R> Response class when success
 * @param <E> Response class when error
 */
class LiveDataCallAdapter<R, E>(
    private val responseType: Type,
    private val errorType: Type,
    private val annotations: Array<Annotation>,
    private val retrofit: Retrofit
) : CallAdapter<R, LiveData<ApiResponse<R, E>>> {
	
	override fun responseType(): Type = responseType
	
	override fun adapt(call: retrofit2.Call<R>): LiveData<ApiResponse<R, E>> {
		val customCallAdapter =
			ResponseCallAdapter<R, E>(
                call,
                retrofit,
                errorType,
                annotations
            )
		
		return object : LiveData<ApiResponse<R, E>>() {
			var started = AtomicBoolean(false)
			
			override fun onActive() {
				super.onActive()
				if (started.compareAndSet(false, true)) {
					customCallAdapter.enqueue(object :
                        Callback<R, E?> {
                        override fun onResponse(response: Response<R, E?>) {
                            postValue(
                                ApiResponse(
                                    response
                                )
                            )
                        }
                        
                        override fun onFailure(e: IOException) {
                            postValue(
                                ApiResponse(
                                    e
                                )
                            )
                        }
                    })
				}
			}
		}
	}
}
