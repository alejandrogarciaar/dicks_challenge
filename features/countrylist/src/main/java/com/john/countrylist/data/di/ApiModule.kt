package com.john.countrylist.data.di

import com.john.countrylist.data.api.CountryListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {
    @Provides
    fun providesCountryListApi(retrofit: Retrofit): CountryListApi {
        return retrofit.create(CountryListApi::class.java)
    }
}