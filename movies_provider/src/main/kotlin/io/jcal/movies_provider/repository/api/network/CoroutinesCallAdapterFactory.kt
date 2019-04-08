package io.jcal.movies_provider.repository.api.network

/*
 * Copyright (C) 2016 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Inject

/**
 * A [CallAdapter.Factory] for use with Kotlin coroutines.
 *
 * Adding this class to [Retrofit] allows you to return [Deferred] from
 * service methods.
 *
 *     interface MyService {
 *       &#64;GET("user/me")
 *       Deferred&lt;User&gt; getUser()
 *     }
 *
 * There are two configurations supported for the [Deferred] type parameter:
 *
 * * Direct body (e.g., `Deferred<User>`) returns the deserialized body for 2XX responses, throws
 * [HttpException] errors for non-2XX responses, and throws [IOException][java.io.IOException] for
 * network errors.
 * * Response wrapped body (e.g., `Deferred<Response<User>>`) returns a [Response] object for all
 * HTTP responses and throws [IOException][java.io.IOException] for network errors
 */

class CoroutinesCallAdapterFactory @Inject constructor() : CallAdapter.Factory() {
    companion object {
        @JvmStatic
        @JvmName("create")
        operator fun invoke() = CoroutinesCallAdapterFactory()

        private const val FIRST_GENERIC_ARGUMENT = 0
        private const val SECOND_GENERIC_ARGUMENT = 1
    }

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (Deferred::class.java != getRawType(returnType)) {
            return null
        }

        if (returnType !is ParameterizedType) {
            throw IllegalStateException(
                "Deferred return type must be parameterized as Deferred<Foo> or Deferred<out Foo>"
            )
        }

        val observableType = CallAdapter.Factory.getParameterUpperBound(
            FIRST_GENERIC_ARGUMENT,
            returnType
        )

        val rawObservableType = getRawType(observableType)

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

        return CoroutinesCallAdapter<Any, Any>(responseType, errorType, annotations, retrofit)
    }


    private class CoroutinesCallAdapter<R, E>(
        private val responseType: Type,
        private val errorType: Type,
        private val annotations: Array<Annotation>,
        private val retrofit: Retrofit
    ) : CallAdapter<R, Deferred<ApiResponse<R, E>>> {

        override fun responseType() = responseType

        override fun adapt(retrofitCall: retrofit2.Call<R>): Deferred<ApiResponse<R, E>> {
            val deferred = CompletableDeferred<ApiResponse<R, E>>()

            val call = ResponseCallAdapter<R, E>(
                call = retrofitCall,
                retrofit = retrofit,
                errorType = errorType,
                annotations = annotations
            )

            deferred.invokeOnCompletion {
                if (deferred.isCancelled) {
                    call.cancel()
                }
            }

            call.enqueue(object : Callback<R, E?> {
                override fun onResponse(response: Response<R, E?>) {
                    deferred.complete(ApiResponse(response))
                }

                override fun onFailure(e: IOException) {
                    deferred.completeExceptionally(e)
                }
            })

            return deferred
        }
    }


}