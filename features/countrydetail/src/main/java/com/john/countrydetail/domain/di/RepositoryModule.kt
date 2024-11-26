package com.john.countrydetail.domain.di

import com.john.countrydetail.domain.repository.CountryDetailRepository
import com.john.countrydetail.domain.repository.impl.CountryDetailRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    fun bindsCountryDetailRepository(
        impl: CountryDetailRepositoryImpl
    ): CountryDetailRepository
}