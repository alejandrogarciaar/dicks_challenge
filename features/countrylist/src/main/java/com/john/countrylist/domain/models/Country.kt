package com.john.countrylist.domain.models

internal data class Country(
    val name: Name,
    val capital: List<String>?,
    val flags: Flags
) {
    data class Name(
        val common: String,
        val official: String
    )

    data class Flags(
        val png: String,
        val svg: String
    )
}
