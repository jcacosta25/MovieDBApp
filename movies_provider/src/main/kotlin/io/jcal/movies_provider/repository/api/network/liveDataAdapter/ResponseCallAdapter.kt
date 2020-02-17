package io.jcal.movies_provider.repository.api.network.liveDataAdapter

import java.io.IOException
import java.lang.reflect.Type
import java.util.concurrent.*
import okhttp3.Headers
import okhttp3.Request
import retrofit2.Retrofit

class ResponseCallAdapter<R, E>(
    private val call: retrofit2.Call<R>,
    private val retrofit: Retrofit,
    private val errorType: Type,
    private val annotations: Array<Annotation>
) : Call<R, E> {
    private val executor: Executor

    init {
        executor = getExecutor()
    }

    @Throws(IOException::class)
    override fun execute(): Response<R, E?> {
        val response = call.execute()
        return object :
            Response<R, E?> {

            override val isSuccessful: Boolean
                get() = response.isSuccessful

            override fun body(): R = response.body()!!

            override fun error(): E? {
                val converter = retrofit.responseBodyConverter<E>(errorType, annotations)
                return try {
                    val errorBody = response.errorBody()
                    if (errorBody != null) {
                        converter.convert(errorBody)
                    } else {
                        null
                    }
                } catch (e: IOException) {
                    null
                }
            }

            override fun code(): Int = response.code()

            override fun headers(): Headers = response.headers()

            // This is going to be used to get a common analytics endpoint without dynamic parameters.
            override fun endpointPath(): String = response.raw().request().url().toString()

            override fun message(): String = response.message()
        }
    }

    override fun enqueue(callback: Callback<R, E?>) {
        call.enqueue(object : retrofit2.Callback<R> {
            override fun onResponse(call: retrofit2.Call<R>, response: retrofit2.Response<R>) {

                executor.execute {
                    callback.onResponse(object :
                        Response<R, E?> {

                        override val isSuccessful: Boolean
                            get() = response.isSuccessful

                        override fun body(): R {
                            return response.body()!!
                        }

                        override fun error(): E? {
                            return try {
                                val converter =
                                    retrofit.responseBodyConverter<E>(errorType, annotations)
                                val errorBody = response.errorBody()
                                if (errorBody != null) {
                                    converter.convert(errorBody)
                                } else {
                                    null
                                }
                            } catch (e: IOException) {
                                null
                            }
                        }

                        override fun code(): Int = response.code()

                        override fun headers(): Headers = response.headers()

                        override fun endpointPath(): String =
                            response.raw().request().url().toString()

                        override fun message(): String = response.message()
                    })
                }
            }

            override fun onFailure(call: retrofit2.Call<R>, t: Throwable) =
                executor.execute { callback.onFailure(IOException(t)) }
        })
    }

    override fun cancel() = call.cancel()

    override fun request(): Request = call.request()

    private fun getExecutor(): Executor {
        var executor = retrofit.callbackExecutor()
        if (executor == null) {
            executor = Executor { command -> command.run() }
        }
        return executor
    }
}
