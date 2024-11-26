package com.john.countrydetail.data.models

import com.google.gson.annotations.SerializedName

internal data class CountryDetailDTO(
    @SerializedName("name") val name: NameDTO,
    @SerializedName("flags") val flags: FlagsDTO,
    @SerializedName("currencies") val currencies: Map<String, CurrencyDTO>,
    @SerializedName("capital") val capital: List<String>,
    @SerializedName("region") val region: String?,
    @SerializedName("subregion") val subregion: String?,
    @SerializedName("languages") val languages: Map<String, String>,
    @SerializedName("population") val population: Long,
    @SerializedName("car") val car: CarDTO,
    @SerializedName("coatOfArms") val coatOfArms: CoatOfArmsDTO
) {
    data class NameDTO(
        @SerializedName("common") val common: String?,
        @SerializedName("official") val official: String?
    )

    data class FlagsDTO(
        @SerializedName("png") val png: String?,
        @SerializedName("svg") val svg: String?
    )

    data class CurrencyDTO(
        @SerializedName("name") val name: String?,
        @SerializedName("symbol") val symbol: String?
    )

    data class CarDTO(
        @SerializedName("side") val side: String?
    )

    data class CoatOfArmsDTO(
        @SerializedName("png") val png: String?,
        @SerializedName("svg") val svg: String?
    )
}
