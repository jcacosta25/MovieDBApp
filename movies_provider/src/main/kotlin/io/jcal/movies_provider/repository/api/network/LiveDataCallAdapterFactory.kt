package io.jcal.movies_provider.repository.api.network

import androidx.lifecycle.LiveData
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Inject

class LiveDataCallAdapterFactory @Inject constructor() : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (CallAdapter.Factory.getRawType(returnType) != LiveData::class.java) {
            return null
        }

        val observableType = CallAdapter.Factory.getParameterUpperBound(
            FIRST_GENERIC_ARGUMENT,
            returnType as ParameterizedType
        )

        val rawObservableType = CallAdapter.Factory.getRawType(observableType)
        if (rawObservableType != ApiResponse::class.java) {
            throw IllegalArgumentException("Type must be an ApiResponse class")
        }

        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("ApiResponse must be parametrized")
        }

        val responseType = CallAdapter.Factory.getParameterUpperBound(
            FIRST_GENERIC_ARGUMENT,
            observableType
        )

        val errorType = CallAdapter.Factory.getParameterUpperBound(
            SECOND_GENERIC_ARGUMENT,
            observableType
        )

        return LiveDataCallAdapter<Any, Any>(responseType, errorType, annotations, retrofit)
    }

    companion object {

        private val FIRST_GENERIC_ARGUMENT = 0
        private val SECOND_GENERIC_ARGUMENT = 1
    }
}