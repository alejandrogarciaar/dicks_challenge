package com.john.countrylist.data.api

import com.john.countrylist.data.models.CountryDTO
import retrofit2.http.GET
import retrofit2.http.Path

internal interface CountryListApi {
    @GET("all?fields=name,flags")
    suspend fun getCountries(): List<CountryDTO>

    @GET("name/{name}")
    suspend fun getCountriesByQuery(@Path("name") query: String): List<CountryDTO>
}