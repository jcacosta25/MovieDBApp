package io.jcal.movies_provider.repository.api.network.coroutinesAdapter

import okhttp3.Headers
import java.io.IOException

sealed class NetworkResponse<T, U> {
    /**
     * A request that resulted in a response with a 2xx status code that has a body.
     */
    data class Success<T, U>(val body: T, val headers: Headers? = null) :
        NetworkResponse<T, U>()

    /**
     * A request that resulted in a response with a non-2xx status code.
     */
    data class ServerError<T, U>(
        val body: T? = null,
        val errorBody: U? = null,
        val code: Int,
        val headers: Headers? = null
    ) : NetworkResponse<T, U>()

    /**
     * A request that didn't result in a response.
     */
    data class NetworkError<T, U>(val error: IOException) : NetworkResponse<T, U>()
}
