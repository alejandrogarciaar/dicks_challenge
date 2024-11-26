package com.john.countrydetail.domain.models

internal data class CountryDetail(
    val name: Name,
    val flags: Flags,
    val currencies: Map<String, Currency>,
    val capital: List<String>,
    val region: String?,
    val subregion: String?,
    val languages: Map<String, String>,
    val population: Long,
    val car: Car,
    val coatOfArms: CoatOfArms
) {
    data class Name(
        val common: String,
        val official: String
    )

    data class Flags(
        val png: String,
        val svg: String
    )

    data class Currency(
        val name: String,
        val symbol: String
    )

    data class Car(
        val side: String
    )

    data class CoatOfArms(
        val png: String,
        val svg: String
    )
}