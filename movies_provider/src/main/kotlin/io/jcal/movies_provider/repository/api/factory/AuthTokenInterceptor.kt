package io.jcal.movies_provider.repository.api.factory

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class AuthTokenInterceptor @Inject constructor(val apiKey: String) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .url(request.url().newBuilder().addQueryParameter(API_KEY, apiKey).build())
            .build()
        return chain.proceed(request)
    }

    companion object {
        const val API_KEY = "api_key"
    }
}