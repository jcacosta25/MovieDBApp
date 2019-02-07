package io.jcal.movies_provider.repository.api.network

import okhttp3.Request
import java.io.IOException

/**
 * A substitute for [retrofit2.Call] that holds a typed response body and a typed error body.
 *
 * @param <R> the type of the response body
 * @param <E> the type of the error body
</E></R> */
interface Call<R, E> {
    /**
     * Synchronously send the request and return its response.
     *
     * @throws IOException      if a problem occurred talking to the server.
     * @throws RuntimeException - (and subclasses) if an unexpected error occurs creating the request or
     * decoding the response.
     */
    @Throws(IOException::class)
    fun execute(): Response<R, E?>

    /**
     * Asynchronously send the request and notify callback of its response or if an error occurred talking to
     * the server, creating the request, or processing the response.
     */
    fun enqueue(callback: Callback<R, E?>)

    /**
     * Cancel this call. An attempt will be made to cancel in-flight calls, and if the call has not
     * yet been executed it never will be.
     */
    fun cancel()


    /** see [retrofit2.Call.request]
     * @return The original HTTP request.
     */
    fun request(): Request
}
