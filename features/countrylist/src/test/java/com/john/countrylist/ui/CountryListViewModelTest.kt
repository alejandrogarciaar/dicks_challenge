package com.john.countrylist.ui

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.john.countrylist.domain.models.Country
import com.john.countrylist.domain.repository.CountryListRepository
import com.john.countrylist.ui.states.CountryListUiState
import com.john.countrylist.ui.viewModel.CountryListViewModel
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CountryListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var repository: CountryListRepository

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: CountryListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this@CountryListViewModelTest, relaxed = true)
        viewModel = CountryListViewModel(repository, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `Getting country list should notify ui state properly`() = runTest {
        val result = arrayListOf<CountryListUiState>()
        coEvery { repository.getCountries() }.returns(flowOf(listOf(businessModel)))

        val job = launch(testDispatcher) { viewModel.uiState.toList(result) }
        viewModel.getCountryList()
        job.cancel()

        assertEquals(2, result.size)
        assertTrue(
            (result.last() as? CountryListUiState.ShowCountryList)?.data == listOf(businessModel)
        )
    }

    @Test
    fun `Getting country list should notify error ui state properly when repository fails`() =
        runTest {
            val result = arrayListOf<CountryListUiState>()
            coEvery { repository.getCountries() }.returns(flow { throw Exception("Something went wrong") })

            val job = launch(testDispatcher) { viewModel.uiState.toList(result) }
            viewModel.getCountryList()
            job.cancel()

            assertEquals(2, result.size)
            assertTrue(result.last() is CountryListUiState.ShowError)
        }

    @Test
    fun `Getting country list by query should notify ui state properly`() = runTest {
        val result = arrayListOf<CountryListUiState>()
        coEvery { repository.getCountries() }.returns(flowOf(listOf(businessModel)))
        coEvery { repository.getCountriesByQuery("Query") }.returns(
            flowOf(
                listOf(
                    searchBusinessModel
                )
            )
        )

        val job = launch(testDispatcher) { viewModel.uiState.toList(result) }
        viewModel.getCountriesByQuery("Query")
        job.cancel()

        assertTrue(
            (result.last() as? CountryListUiState.ShowCountryList)?.data == listOf(
                searchBusinessModel
            )
        )
    }

    @Test
    fun `Getting country list by query should notify error ui state properly when repository fails`() =
        runTest {
            val result = arrayListOf<CountryListUiState>()
            coEvery { repository.getCountriesByQuery("Query") }.returns(flow { throw Exception("Something went wrong") })

            val job = launch(testDispatcher) { viewModel.uiState.toList(result) }
            viewModel.getCountriesByQuery("Query")
            job.cancel()

            assertTrue(result.last() is CountryListUiState.ShowError)
        }

    private companion object {
        const val COMMON = "South Georgia"
        const val OFFICIAL = "South Georgia and the South Sandwich Islands"
        const val PNG = "https://flagcdn.com/w320/gs.png"
        const val SVG = "https://flagcdn.com/gs.svg"
        const val CAPITAL = "King Edward Point"

        const val COMMON_BY_SEARCH = "South Georgia"
        const val OFFICIAL_BY_SEARCH = "South Georgia and the South Sandwich Islands"
        const val PNG_BY_SEARCH = "https://flagcdn.com/w320/gs.png"
        const val SVG_BY_SEARCH = "https://flagcdn.com/gs.svg"
        const val CAPITAL_BY_SEARCH = "King Edward Point"

        val businessModel = Country(
            name = Country.Name(
                common = COMMON,
                official = OFFICIAL
            ),
            capital = listOf(CAPITAL),
            flags = Country.Flags(
                png = PNG,
                svg = SVG
            )
        )

        val searchBusinessModel = Country(
            name = Country.Name(
                common = COMMON_BY_SEARCH,
                official = OFFICIAL_BY_SEARCH
            ),
            capital = listOf(CAPITAL_BY_SEARCH),
            flags = Country.Flags(
                png = PNG_BY_SEARCH,
                svg = SVG_BY_SEARCH
            )
        )
    }
}