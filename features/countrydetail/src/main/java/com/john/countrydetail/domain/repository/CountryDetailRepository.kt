package com.john.countrydetail.domain.repository

import com.john.countrydetail.domain.models.CountryDetail
import kotlinx.coroutines.flow.Flow

internal interface CountryDetailRepository {
    fun getCountryDetail(countryName: String): Flow<CountryDetail>
}