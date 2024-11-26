package com.john.countrylist.domain.repository

import com.john.countrylist.domain.models.Country
import kotlinx.coroutines.flow.Flow

internal interface CountryListRepository {
    fun getCountries(): Flow<List<Country>>
    fun getCountriesByQuery(query: String): Flow<List<Country>>
}