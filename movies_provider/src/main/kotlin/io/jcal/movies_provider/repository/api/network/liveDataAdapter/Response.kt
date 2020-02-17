package io.jcal.movies_provider.repository.api.network.liveDataAdapter

import androidx.annotation.Nullable
import okhttp3.Headers

/**
 * A substitute for [retrofit2.Response] which has a typed error body in addition to the typed response
 * body.
 *
 * @param <R> the type of the response body
 * @param <E> the type of the error body
</E></R> */
interface Response<R, E> {

    /**
     * Checks whether this request was successful, by delegating to the underlying retrofit Response.
     * Retrofit considers response codes >= 200 and =< 300 as successful.
     *
     * @return true if the request was successful; false otherwise
     */
    val isSuccessful: Boolean

    /**
     * @return the response body
     */
    fun body(): R

    /**
     * @return the error; possibly `null`
     */
    @Nullable
    fun error(): E

    /**
     * @return http code
     */
    fun code(): Int

    /**
     * @return headers
     */
    fun headers(): Headers

    /**
     * gets the path of the endpoint.  This will mostly be used for analytics
     * @return the path of the endpoint with generic parameters
     */
    fun endpointPath(): String

    /** wraps [retrofit2.Response.message]  */
    fun message(): String
}
