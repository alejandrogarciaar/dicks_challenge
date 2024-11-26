package com.john.countrydetail.domain.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.john.countrydetail.data.api.CountryDetailApi
import com.john.countrydetail.data.models.CountryDetailDTO
import com.john.countrydetail.domain.mapper.CountryDetailMapper
import com.john.countrydetail.domain.repository.impl.CountryDetailRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CountryDetailRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var countryDetailApi: CountryDetailApi

    private val mapper = CountryDetailMapper()
    private lateinit var repository: CountryDetailRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this@CountryDetailRepositoryTest, relaxed = true)
        repository = CountryDetailRepositoryImpl(countryDetailApi, mapper)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `Getting country details should call api service properly`() = runTest {
        coEvery { countryDetailApi.getCountryDetail("Colombia") }.returns(listOf(response))

        val response = repository.getCountryDetail("Colombia").first()

        coVerify { countryDetailApi.getCountryDetail("Colombia") }
        assertNotNull(response)
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

        val response = CountryDetailDTO(
            name = CountryDetailDTO.NameDTO(
                common = NAME_COMMON,
                official = NAME_OFFICIAL
            ),
            flags = CountryDetailDTO.FlagsDTO(
                png = FLAG_PNG,
                svg = FLAG_SVG
            ),
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
            car = CountryDetailDTO.CarDTO(
                side = CAR_DRIVE_SIDE
            ),
            coatOfArms = CountryDetailDTO.CoatOfArmsDTO(
                png = COAT_OF_ARMS_PNG,
                svg = COAT_OF_ARMS_SVG
            )
        )
    }
}