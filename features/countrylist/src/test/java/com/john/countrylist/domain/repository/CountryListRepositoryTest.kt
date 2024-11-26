package com.john.countrylist.domain.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.john.countrylist.data.api.CountryListApi
import com.john.countrylist.data.models.CountryDTO
import com.john.countrylist.domain.mapper.CountryMapper
import com.john.countrylist.domain.repository.impl.CountryListRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CountryListRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var countryListApi: CountryListApi

    private val mapper = CountryMapper()
    private lateinit var repository: CountryListRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this@CountryListRepositoryTest, relaxed = true)
        repository = CountryListRepositoryImpl(countryListApi, mapper)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `Getting countries should call api service properly`() = runTest {
        coEvery { countryListApi.getCountries() }.returns(listOf(response))

        val response = repository.getCountries().first()

        coVerify { countryListApi.getCountries() }
        assertTrue(response.isNotEmpty())
    }

    @Test
    fun `Getting countries by queries should call api service properly`() = runTest {
        coEvery { countryListApi.getCountriesByQuery(query = "Something") }.returns(listOf(response))

        val response = repository.getCountriesByQuery("Something").first()

        coVerify { countryListApi.getCountriesByQuery("Something") }
        assertTrue(response.isNotEmpty())
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