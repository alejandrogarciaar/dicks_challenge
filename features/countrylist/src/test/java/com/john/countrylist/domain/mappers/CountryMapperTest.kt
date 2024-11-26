package com.john.countrylist.domain.mappers

import com.john.countrylist.data.models.CountryDTO
import com.john.countrylist.domain.models.Country
import com.john.test_common.assertThrowsIllegalArgumentExceptionWithMessage
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CountryMapperTest {

    private lateinit var mapper: CountryMapper

    @Before
    fun setup() {
        mapper = CountryMapper()
    }

    @Test
    fun `Mapping CountryDTO should retrieve business model if all attributes exist`() {
        val businessModel = mapper.map(response)

        assertEquals(
            Country(
                name = Country.Name(
                    common = COMMON,
                    official = OFFICIAL
                ),
                capital = listOf(CAPITAL),
                flags = Country.Flags(
                    png = PNG,
                    svg = SVG
                )
            ),
            businessModel
        )
    }

    @Test
    fun `Mapping CountryDTO should throw IllegalArgument exception when name common is null`() {
        val name = name.copy(common = null)
        val newResponse = response.copy(name = name)

        assertThrowsIllegalArgumentExceptionWithMessage("name.common is required") {
            mapper.map(newResponse)
        }
    }

    @Test
    fun `Mapping CountryDTO should throw IllegalArgument exception when name common is empty`() {
        val name = name.copy(common = "")
        val newResponse = response.copy(name = name)

        assertThrowsIllegalArgumentExceptionWithMessage("name.common cannot be blank or empty") {
            mapper.map(newResponse)
        }
    }

    @Test
    fun `Mapping CountryDTO should throw IllegalArgument exception when name official is null`() {
        val name = name.copy(official = null)
        val newResponse = response.copy(name = name)

        assertThrowsIllegalArgumentExceptionWithMessage("name.official is required") {
            mapper.map(newResponse)
        }
    }

    @Test
    fun `Mapping CountryDTO should throw IllegalArgument exception when name official is empty`() {
        val name = name.copy(official = "")
        val newResponse = response.copy(name = name)

        assertThrowsIllegalArgumentExceptionWithMessage("name.official cannot be blank or empty") {
            mapper.map(newResponse)
        }
    }

    @Test
    fun `Mapping CountryDTO should throw IllegalArgument exception when flags png is null`() {
        val flags = flags.copy(png = null)
        val newResponse = response.copy(flags = flags)

        assertThrowsIllegalArgumentExceptionWithMessage("flags.png is required") {
            mapper.map(newResponse)
        }
    }

    @Test
    fun `Mapping CountryDTO should throw IllegalArgument exception when flags png is empty`() {
        val flags = flags.copy(png = "")
        val newResponse = response.copy(flags = flags)

        assertThrowsIllegalArgumentExceptionWithMessage("flags.png cannot be blank or empty") {
            mapper.map(newResponse)
        }
    }

    @Test
    fun `Mapping CountryDTO should throw IllegalArgument exception when flags svg is null`() {
        val flags = flags.copy(svg = null)
        val newResponse = response.copy(flags = flags)

        assertThrowsIllegalArgumentExceptionWithMessage("flags.svg is required") {
            mapper.map(newResponse)
        }
    }

    @Test
    fun `Mapping CountryDTO should throw IllegalArgument exception when flags svg is empty`() {
        val flags = flags.copy(svg = "")
        val newResponse = response.copy(flags = flags)

        assertThrowsIllegalArgumentExceptionWithMessage("flags.svg cannot be blank or empty") {
            mapper.map(newResponse)
        }
    }

    private companion object {
        const val COMMON = "South Georgia"
        const val OFFICIAL = "South Georgia and the South Sandwich Islands"
        const val PNG = "https://flagcdn.com/w320/gs.png"
        const val SVG = "https://flagcdn.com/gs.svg"
        const val CAPITAL = "King Edward Point"

        val flags = CountryDTO.FlagsDTO(
            png = PNG,
            svg = SVG
        )
        val name = CountryDTO.NameDTO(
            common = COMMON,
            official = OFFICIAL
        )
        val response = CountryDTO(
            name = name,
            capital = listOf(CAPITAL),
            flags = flags
        )
    }
}