package com.john.countrylist.data.models

import com.google.gson.annotations.SerializedName

internal data class CountryDTO(
    @SerializedName("name")
    val name: NameDTO,
    @SerializedName("capital")
    val capital: List<String>?,
    @SerializedName("flags")
    val flags: FlagsDTO
) {
    data class NameDTO(
        @SerializedName("common") val common: String?,
        @SerializedName("official") val official: String?
    )

    data class FlagsDTO(
        @SerializedName("png")
        val png: String?,
        @SerializedName("svg")
        val svg: String?
    )
}
