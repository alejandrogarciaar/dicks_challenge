package com.john.countrylist.domain.repository.impl

import com.john.countrylist.data.api.CountryListApi
import com.john.countrylist.domain.mapper.CountryMapper
import com.john.countrylist.domain.models.Country
import com.john.countrylist.domain.repository.CountryListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class CountryListRepositoryImpl @Inject constructor(
    private val countryListApi: CountryListApi,
    private val countryListMapper: CountryMapper
) : CountryListRepository {

    override fun getCountries(): Flow<List<Country>> = flow {
        val response = countryListApi.getCountries()
        emit(response.map { countryListMapper.map(it) })
    }

    override fun getCountriesByQuery(query: String): Flow<List<Country>> = flow {
        val response = countryListApi.getCountriesByQuery(query)
        emit(response.map { countryListMapper.map(it) })
    }
}