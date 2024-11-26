package com.john.countrylist.domain.mappers

import com.john.core.extensions.requireNotEmptyNotNull
import com.john.countrylist.data.models.CountryDTO
import com.john.countrylist.domain.models.Country
import javax.inject.Inject

internal class CountryMapper @Inject constructor() {
    fun map(input: CountryDTO): Country {
        return try {
            Country(
                name = mapName(input.name),
                capital = input.capital,
                flags = mapFlags(input.flags)
            )
        } catch (exception: IllegalArgumentException) {
            throw exception
        }
    }

    private fun mapName(input: CountryDTO.NameDTO): Country.Name {
        return Country.Name(
            common = input.common.requireNotEmptyNotNull("name.common"),
            official = input.official.requireNotEmptyNotNull("name.official"),
        )
    }

    private fun mapFlags(input: CountryDTO.FlagsDTO): Country.Flags {
        return Country.Flags(
            png = input.png.requireNotEmptyNotNull("flags.png"),
            svg = input.svg.requireNotEmptyNotNull("flags.svg")
        )
    }
}