package com.john.countrydetail.actions.di

import com.john.core.navigation.DefaultActionHandler
import com.john.core.navigation.DestinationType
import com.john.core.navigation.DestinationTypeKey
import com.john.countrydetail.actions.CountryDetailActionHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
internal interface CountryDetailActionsModule {
    @Binds
    @IntoMap
    @DestinationTypeKey(DestinationType.COUNTRY_DETAIL)
    fun bindsCountryDetailActionHandler(impl: CountryDetailActionHandler): DefaultActionHandler
}