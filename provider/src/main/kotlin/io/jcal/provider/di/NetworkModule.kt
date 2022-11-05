package io.jcal.provider.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.jcal.provider.repository.api.MovieDBService
import io.jcal.provider.repository.api.factory.AuthTokenInterceptor
import io.jcal.provider.repository.api.factory.ServiceFactory
import io.jcal.provider.repository.api.network.liveDataAdapter.LiveDataCallAdapterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention
@Qualifier
annotation class ApiToken

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
	
	@Provides
	@Singleton
	fun providesHttpUrl(): HttpUrl = ServiceFactory.providesHttpUrl()
	
	@Provides
	@Singleton
	fun providesTokenInterceptor(@ApiToken apiToken: String): AuthTokenInterceptor =
		AuthTokenInterceptor(apiToken)
	
	@Provides
	@Singleton
	fun provideGson(): Gson = GsonBuilder().create()
	
	@Provides
	@Singleton
	fun provideJsonConverterFactory(gson: Gson): Converter.Factory =
		GsonConverterFactory.create(gson)
	
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
	    adapterFactory: LiveDataCallAdapterFactory
	): Retrofit = ServiceFactory.buildRetrofit(url, client, converter, adapterFactory)
	
	@Provides
	@Singleton
	fun provideMovieDbService(retrofit: Retrofit): MovieDBService =
		retrofit.create(MovieDBService::class.java)
}
