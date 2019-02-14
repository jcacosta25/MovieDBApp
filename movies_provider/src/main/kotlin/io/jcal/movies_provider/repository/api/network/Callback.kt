package io.jcal.movies_provider.repository.api.network

import java.io.IOException

/**
 * A substitute for {@link retrofit2.Callback} that has a typed error body as well as response body
 *
 * @param <R> the type of the response body
 * @param <E> the type of the error body
 */
interface Callback<R, E> {
    /**
     * Invoked for a received HTTP response.
     * <p>
     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
     * Call {@link Response#isSuccessful()} to determine if the response indicates success.
     */
    fun onResponse(response: Response<R, E>)

    /**
     * Invoked when a network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response.
     */
    fun onFailure(e: IOException)
}