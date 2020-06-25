package io.jcal.movies_provider.repository.api.network.coroutinesAdapter

import kotlinx.coroutines.delay
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException
import java.io.IOException

/**
 * Retries the given [block] for the specified number of times in the case of [NetworkResponse.NetworkError]
 *
 * @param T The success body type of [NetworkResponse]
 * @param U The error body type of [NetworkResponse]
 * @param times The number of times this request should be retried
 * @param initialDelay The initial amount of time to wait before retrying
 * @param maxDelay The max amount of time to wait before retrying
 * @param factor Multiply current delay time with this on each retry
 * @param block The suspending function to be retried
 * @return The NetworkResponse value whether it be successful or failed after retrying
 */
@JvmOverloads
suspend inline fun <T : Any, U : Any> executeWithRetry(
    times: Int = 10,
    initialDelay: Long = 100, // 0.1 second
    maxDelay: Long = 1000, // 1 second
    factor: Double = 2.0,
    block: () -> NetworkResponse<T, U>
): NetworkResponse<T, U> {
    var currentDelay = initialDelay
    repeat(times - 1) {
        when (val response = block()) {
            is NetworkResponse.NetworkError -> {
                delay(currentDelay)
                currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
            }
            else -> return response
        }
    }
    return block() // last attempt
}

/**
 * Overloaded invoke operator to get the successful body or null in NetworkResponse class
 *
 * @param T the success body type of [NetworkResponse]
 * @param U the error body type of [NetworkResponse]
 *
 * Example:
 * val usersResponse = executeWithRetry { getUsers() }
 *
 * println(usersResponse() ?: "No users found")
 */
operator fun <T : Any, U : Any> NetworkResponse<T, U>.invoke(): T? {
    return if (this is NetworkResponse.Success) body else null
}

internal const val UNKNOWN_ERROR_RESPONSE_CODE = 520

internal fun <S : Any, E : Any> HttpException.extractFromHttpException(errorConverter: Converter<ResponseBody, E>): NetworkResponse<S, E> {
    val error = response()?.errorBody()
    val responseCode = response()?.code() ?: UNKNOWN_ERROR_RESPONSE_CODE
    val headers = response()?.headers()
    val errorBody = when {
        error == null -> null // No error content available
        error.contentLength() == 0L -> null // Error content is empty
        else -> try {
            // There is error content present, so we should try to extract it
            errorConverter.convert(error)
        } catch (e: Exception) {
            // If unable to extract content, return with a null body and don't parse further
            return NetworkResponse.ServerError(body = null, code = responseCode, headers = headers)
        }
    }
    return NetworkResponse.ServerError(
        errorBody = errorBody,
        code = responseCode,
        headers = headers
    )
}

internal fun <S : Any, E : Any> Throwable.extractNetworkResponse(errorConverter: Converter<ResponseBody, E>): NetworkResponse<S, E> {
    return when (this) {
        is IOException -> NetworkResponse.NetworkError(this)
        is HttpException -> extractFromHttpException<S, E>(errorConverter)
        else -> throw this
    }
}
