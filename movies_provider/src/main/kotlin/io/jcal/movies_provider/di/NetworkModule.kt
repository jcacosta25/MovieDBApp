package io.jcal.movies_provider.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.jcal.movies_provider.BuildConfig
import io.jcal.movies_provider.repository.api.MovieDBService
import io.jcal.movies_provider.repository.api.factory.AuthTokenInterceptor
import io.jcal.movies_provider.repository.api.factory.ServiceFactory
import io.jcal.movies_provider.repository.api.network.LiveDataCallAdapterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesHttpUrl(): HttpUrl = ServiceFactory.providesHttpUrl()

    @Provides
    @Singleton
    fun providesTokenInterceptor(@Named(API_TOKEN_PROPERTY) apiToken: String = BuildConfig.ApiKey): AuthTokenInterceptor =
        AuthTokenInterceptor(apiToken)

    @Provides
    @Singleton
    @Named(MOSHI_PROPERTY)
    fun provideMoshi(): Moshi = Moshi.Builder().build()


    @Provides
    @Singleton
    fun provideJsonConverterFactory(@Named(MOSHI_PROPERTY) moshi: Moshi): Converter.Factory =
        MoshiConverterFactory.create(moshi)


    @Provides
    @Singleton
    fun providesHttpClient(tokenInterceptor: AuthTokenInterceptor): OkHttpClient =
        ServiceFactory.buildOkHttpClient(tokenInterceptor)


    @Provides
    @Singleton
    fun provideRetrofit(
        url: HttpUrl,
        client: OkHttpClient,
        converter: Converter.Factory,
        liveDataCallAdapterFactory: LiveDataCallAdapterFactory
    ): Retrofit = ServiceFactory.buildRetrofit(url, client, converter, liveDataCallAdapterFactory)

    @Provides
    @Singleton
    fun provideMovieDbService(retrofit: Retrofit): MovieDBService =
        retrofit.create(MovieDBService::class.java)

    companion object {
        const val API_TOKEN_PROPERTY = "api_token"
        const val MOSHI_PROPERTY = "retrofit_moshi"
    }
}