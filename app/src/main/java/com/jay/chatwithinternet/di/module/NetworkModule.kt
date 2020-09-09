package com.jay.chatwithinternet.di.module


import com.jay.chatwithinternet.BuildConfig
import com.jay.chatwithinternet.utils.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun gsonConverterFactory() = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun buildRetrofit(httpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(httpClient)
            .build()

    // region Http Clients
    @Provides
    @Singleton
    fun provideOkHttp(logger: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(logger)
            .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
}