package com.john.countrylist.domain.di

import com.john.countrylist.domain.repository.CountryListRepository
import com.john.countrylist.domain.repository.impl.CountryListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    fun bindsCountryListRepository(
        impl: CountryListRepositoryImpl
    ): CountryListRepository
}
