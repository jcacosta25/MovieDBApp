package io.jcal.movies_provider.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.jcal.movies_provider.repository.api.MovieDBService
import io.jcal.movies_provider.repository.api.factory.AuthTokenInterceptor
import io.jcal.movies_provider.repository.api.factory.ServiceFactory
import io.jcal.movies_provider.repository.api.network.liveDataAdapter.LiveDataCallAdapterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class NetworkModule {

    @Provides
    fun providesHttpUrl(): HttpUrl = ServiceFactory.providesHttpUrl()

    @Provides
    fun providesTokenInterceptor(@Named(API_TOKEN_PROPERTY) apiToken: String): AuthTokenInterceptor =
        AuthTokenInterceptor(apiToken)

    @Provides
    @Named(GSON_PROPERTY)
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideJsonConverterFactory(@Named(GSON_PROPERTY) gson: Gson): Converter.Factory =
        GsonConverterFactory.create(gson)

    @Provides
    fun providesHttpClient(tokenInterceptor: AuthTokenInterceptor): OkHttpClient =
        ServiceFactory.buildOkHttpClient(tokenInterceptor)

    @Provides
    fun provideRetrofit(
        url: HttpUrl,
        client: OkHttpClient,
        converter: Converter.Factory,
        adapterFactory: LiveDataCallAdapterFactory
    ): Retrofit = ServiceFactory.buildRetrofit(url, client, converter, adapterFactory)

    @Provides
    fun provideMovieDbService(retrofit: Retrofit): MovieDBService =
        retrofit.create(MovieDBService::class.java)

    companion object {
        const val API_TOKEN_PROPERTY = "api_token"
        const val GSON_PROPERTY = "retrofit_gson"
    }
}
