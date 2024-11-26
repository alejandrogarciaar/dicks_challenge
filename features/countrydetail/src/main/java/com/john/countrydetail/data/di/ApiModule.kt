package com.john.countrydetail.data.di

import com.john.countrydetail.data.api.CountryDetailApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {
    @Provides
    fun providesCountryDetailApi(retrofit: Retrofit): CountryDetailApi {
        return retrofit.create(CountryDetailApi::class.java)
    }
}