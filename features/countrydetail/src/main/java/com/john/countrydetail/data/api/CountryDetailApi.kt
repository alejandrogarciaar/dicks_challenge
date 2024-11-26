package com.john.countrydetail.data.api

import com.john.countrydetail.data.models.CountryDetailDTO
import retrofit2.http.GET
import retrofit2.http.Path

internal interface CountryDetailApi {
    @GET("name/{name}?fields=name,capital,flags,region,subregion,languages,currencies,population,car,coatOfArms")
    suspend fun getCountryDetail(@Path("name") name: String): List<CountryDetailDTO>
}