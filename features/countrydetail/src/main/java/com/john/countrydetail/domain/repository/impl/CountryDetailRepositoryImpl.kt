package com.john.countrydetail.domain.repository.impl

import com.john.countrydetail.data.api.CountryDetailApi
import com.john.countrydetail.domain.mapper.CountryDetailMapper
import com.john.countrydetail.domain.models.CountryDetail
import com.john.countrydetail.domain.repository.CountryDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class CountryDetailRepositoryImpl @Inject constructor(
    private val countryDetailApi: CountryDetailApi,
    private val countryDetailMapper: CountryDetailMapper
) : CountryDetailRepository {

    override fun getCountryDetail(countryName: String): Flow<CountryDetail> = flow {
        val response = countryDetailApi.getCountryDetail(countryName)
        emit(countryDetailMapper.map(response.first()))
    }
}