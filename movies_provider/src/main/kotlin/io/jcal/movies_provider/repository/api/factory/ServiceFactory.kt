package io.jcal.movies_provider.repository.api.factory

import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import io.jcal.movies_provider.BuildConfig
import io.jcal.movies_provider.repository.api.network.CoroutinesCallAdapterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.*


object ServiceFactory {

    fun buildRetrofit(
        url: HttpUrl,
        client: OkHttpClient,
        converter: Converter.Factory,
        adapter: CallAdapter.Factory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(adapter)
            .addCallAdapterFactory(CoroutinesCallAdapterFactory())
            .addConverterFactory(converter)
            .client(client)
            .build()

    fun buildOkHttpClient(tokenInterceptor: AuthTokenInterceptor): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            httpClientBuilder.addInterceptor(OkHttpProfilerInterceptor())
        }
        return httpClientBuilder
            .addInterceptor(tokenInterceptor)
            .readTimeout(MAX_CONNECTION_TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(MAX_READ_TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    fun providesHttpUrl(): HttpUrl =
        HttpUrl.Builder()
            .scheme(SCHEME)
            .host(HOST)
            .build()


    private const val MAX_READ_TIME_OUT_SECONDS = 60L
    private const val MAX_CONNECTION_TIME_OUT_SECONDS = 60L
    private const val SCHEME = "https"
    private const val HOST = "api.themoviedb.org"
}