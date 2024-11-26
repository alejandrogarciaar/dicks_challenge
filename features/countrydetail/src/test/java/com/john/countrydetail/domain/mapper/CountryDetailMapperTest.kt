package com.john.countrydetail.domain.mapper

import com.john.countrydetail.data.models.CountryDetailDTO
import com.john.countrydetail.domain.models.CountryDetail
import com.john.test_common.assertThrowsIllegalArgumentExceptionWithMessage
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CountryDetailMapperTest {

    private lateinit var mapper: CountryDetailMapper

    @Before
    fun setup() {
        mapper = CountryDetailMapper()
    }

    @Test
    fun `Mapping CountryDetail should retrieve business model if all attributes exist`() {
        val businessModel = mapper.map(response)

        assertEquals(
            CountryDetail(
                name = CountryDetail.Name(
                    common = NAME_COMMON,
                    official = NAME_OFFICIAL
                ),
                flags = CountryDetail.Flags(
                    png = FLAG_PNG,
                    svg = FLAG_SVG
                ),
                currencies = mapOf(
                    "CHF" to CountryDetail.Currency(
                        name = CURRENCY_NAME,
                        symbol = CURRENCY_SYMBOL
                    )
                ),
                capital = listOf(CAPITAL),
                region = REGION,
                subregion = SUBREGION,
                languages = mapOf(
                    "fra" to LANGUAGE_FRENCH,
                    "gsw" to LANGUAGE_SWISS_GERMAN,
                    "ita" to LANGUAGE_ITALIAN,
                    "roh" to LANGUAGE_ROMANSH
                ),
                population = POPULATION,
                car = CountryDetail.Car(side = CAR_DRIVE_SIDE),
                coatOfArms = CountryDetail.CoatOfArms(
                    png = COAT_OF_ARMS_PNG,
                    svg = COAT_OF_ARMS_SVG
                )
            ),
            businessModel
        )
    }

    @Test
    fun `Mapping CountryDetail should throw IllegalArgumentException when name common is null`() {
        val name = name.copy(common = null)
        val response = response.copy(name = name)

        assertThrowsIllegalArgumentExceptionWithMessage("name.common is required") {
            mapper.map(response)
        }
    }

    @Test
    fun `Mapping CountryDetail should throw IllegalArgumentException when name common is empty`() {
        val name = name.copy(common = "")
        val response = response.copy(name = name)

        assertThrowsIllegalArgumentExceptionWithMessage("name.common cannot be blank or empty") {
            mapper.map(response)
        }
    }

    @Test
    fun `Mapping CountryDetail should throw IllegalArgumentException when name official is null`() {
        val name = name.copy(official = null)
        val response = response.copy(name = name)

        assertThrowsIllegalArgumentExceptionWithMessage("name.official is required") {
            mapper.map(response)
        }
    }

    @Test
    fun `Mapping CountryDetail should throw IllegalArgumentException when name official is empty`() {
        val name = name.copy(official = "")
        val response = response.copy(name = name)

        assertThrowsIllegalArgumentExceptionWithMessage("name.official cannot be blank or empty") {
            mapper.map(response)
        }
    }

    @Test
    fun `Mapping CountryDetail should throw IllegalArgumentException when flags png is null`() {
        val flags = flags.copy(png = null)
        val response = response.copy(flags = flags)

        assertThrowsIllegalArgumentExceptionWithMessage("flags.png is required") {
            mapper.map(response)
        }
    }

    @Test
    fun `Mapping CountryDetail should throw IllegalArgumentException when flags png is empty`() {
        val flags = flags.copy(png = "")
        val response = response.copy(flags = flags)

        assertThrowsIllegalArgumentExceptionWithMessage("flags.png cannot be blank or empty") {
            mapper.map(response)
        }
    }

    @Test
    fun `Mapping CountryDetail should throw IllegalArgumentException when region is null`() {
        val response = response.copy(region = null)

        assertThrowsIllegalArgumentExceptionWithMessage("region is required") {
            mapper.map(response)
        }
    }

    @Test
    fun `Mapping CountryDetail should throw IllegalArgumentException when car side is null`() {
        val car = car.copy(side = null)
        val response = response.copy(car = car)

        assertThrowsIllegalArgumentExceptionWithMessage("car.side is required") {
            mapper.map(response)
        }
    }

    @Test
    fun `Mapping CountryDetail should throw IllegalArgumentException when car side is empty`() {
        val car = car.copy(side = "")
        val response = response.copy(car = car)

        assertThrowsIllegalArgumentExceptionWithMessage("car.side cannot be blank or empty") {
            mapper.map(response)
        }
    }

    private companion object {
        const val NAME_COMMON = "Switzerland"
        const val NAME_OFFICIAL = "Switzerland"
        const val FLAG_PNG = "https://flagcdn.com/w320/ch.png"
        const val FLAG_SVG = "https://flagcdn.com/ch.svg"
        const val COAT_OF_ARMS_PNG = "https://mainfacts.com/media/images/coats_of_arms/ch.png"
        const val COAT_OF_ARMS_SVG = "https://mainfacts.com/media/images/coats_of_arms/ch.svg"
        const val CURRENCY_NAME = "Swiss franc"
        const val CURRENCY_SYMBOL = "Fr."
        const val CAPITAL = "Bern"
        const val REGION = "Europe"
        const val SUBREGION = "Western Europe"
        const val LANGUAGE_FRENCH = "French"
        const val LANGUAGE_SWISS_GERMAN = "Swiss German"
        const val LANGUAGE_ITALIAN = "Italian"
        const val LANGUAGE_ROMANSH = "Romansh"
        const val POPULATION = 8654622L
        const val CAR_DRIVE_SIDE = "right"

        val name = CountryDetailDTO.NameDTO(
            common = NAME_COMMON,
            official = NAME_OFFICIAL
        )

        val flags = CountryDetailDTO.FlagsDTO(
            png = FLAG_PNG,
            svg = FLAG_SVG
        )

        val car = CountryDetailDTO.CarDTO(
            side = CAR_DRIVE_SIDE
        )

        val coatOfArms = CountryDetailDTO.CoatOfArmsDTO(
            png = COAT_OF_ARMS_PNG,
            svg = COAT_OF_ARMS_SVG
        )

        val response = CountryDetailDTO(
            name = name,
            flags = flags,
            currencies = mapOf(
                "CHF" to CountryDetailDTO.CurrencyDTO(
                    name = CURRENCY_NAME,
                    symbol = CURRENCY_SYMBOL
                )
            ),
            capital = listOf(CAPITAL),
            region = REGION,
            subregion = SUBREGION,
            languages = mapOf(
                "fra" to LANGUAGE_FRENCH,
                "gsw" to LANGUAGE_SWISS_GERMAN,
                "ita" to LANGUAGE_ITALIAN,
                "roh" to LANGUAGE_ROMANSH
            ),
            population = POPULATION,
            car = car,
            coatOfArms = coatOfArms
        )
    }
}