package com.john.countrydetail.domain.mapper

import com.john.core.extensions.requireNotEmptyNotNull
import com.john.countrydetail.data.models.CountryDetailDTO
import com.john.countrydetail.domain.models.CountryDetail
import javax.inject.Inject

internal class CountryDetailMapper @Inject constructor() {
    fun map(input: CountryDetailDTO): CountryDetail {
        return try {
            CountryDetail(
                name = mapName(input.name),
                flags = mapFlags(input.flags),
                currencies = mapCurrencies(input.currencies),
                capital = input.capital,
                region = input.region.requireNotEmptyNotNull("region"),
                subregion = input.subregion.orEmpty(),
                languages = input.languages,
                population = input.population,
                car = mapCar(input.car),
                coatOfArms = mapCoatOfArms(input.coatOfArms)
            )
        } catch (exception: IllegalArgumentException) {
            // If we need to track something, this is the place
            throw exception
        }
    }

    private fun mapFlags(input: CountryDetailDTO.FlagsDTO): CountryDetail.Flags {
        return CountryDetail.Flags(
            png = input.png.requireNotEmptyNotNull("flags.png"),
            svg = input.svg.requireNotEmptyNotNull("flags.svg")
        )
    }

    private fun mapCar(input: CountryDetailDTO.CarDTO): CountryDetail.Car {
        return CountryDetail.Car(
            side = input.side.requireNotEmptyNotNull("car.side")
        )
    }

    private fun mapCurrencies(currencies: Map<String, CountryDetailDTO.CurrencyDTO>): Map<String, CountryDetail.Currency> {
        return mapOf(
            currencies.keys.first() to mapCurrency(currencies.values.first())
        )
    }

    private fun mapCurrency(input: CountryDetailDTO.CurrencyDTO): CountryDetail.Currency {
        return CountryDetail.Currency(
            name = input.name.requireNotEmptyNotNull("currencies.name"),
            symbol = input.symbol.requireNotEmptyNotNull("currencies.symbol")
        )
    }

    private fun mapName(input: CountryDetailDTO.NameDTO): CountryDetail.Name {
        return CountryDetail.Name(
            common = input.common.requireNotEmptyNotNull("name.common"),
            official = input.official.requireNotEmptyNotNull("name.official")
        )
    }

    private fun mapCoatOfArms(input: CountryDetailDTO.CoatOfArmsDTO): CountryDetail.CoatOfArms {
        return CountryDetail.CoatOfArms(
            png = input.png.orEmpty(),
            svg = input.svg.orEmpty()
        )
    }
}