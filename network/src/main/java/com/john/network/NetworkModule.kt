package com.john.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .callTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun providesRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(DEFAULT_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private const val DEFAULT_TIMEOUT = 30L
    private const val DEFAULT_URL = "https://restcountries.com/v3.1/"
}