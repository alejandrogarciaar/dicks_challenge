package com.john.countrydetail.ui

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.john.countrydetail.domain.models.CountryDetail
import com.john.countrydetail.domain.repository.CountryDetailRepository
import com.john.countrydetail.ui.states.CountryDetailUiState
import com.john.countrydetail.ui.viewModel.CountryDetailViewModel
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
class CountryDetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var repository: CountryDetailRepository

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: CountryDetailViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this@CountryDetailViewModelTest, relaxed = true)
        viewModel = CountryDetailViewModel(repository, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `Getting country detail by name should notify ui state properly`() = runTest {
        val result = arrayListOf<CountryDetailUiState>()
        coEvery { repository.getCountryDetail("Switzerland") }.returns(flowOf(businessModel))

        val job = launch(testDispatcher) { viewModel.uiState.toList(result) }
        viewModel.getCountryDetailByName("Switzerland")
        job.cancel()

        assertEquals(2, result.size)
        assertTrue(
            (result.last() as? CountryDetailUiState.ShowDetail)?.data == businessModel
        )
    }

    @Test
    fun `Getting country detail by name should notify error ui state properly when repository fails`() =
        runTest {
            val result = arrayListOf<CountryDetailUiState>()
            coEvery { repository.getCountryDetail("Switzerland") }.returns(flow { throw Exception("Something went wrong") })

            val job = launch(testDispatcher) { viewModel.uiState.toList(result) }
            viewModel.getCountryDetailByName("Switzerland")
            job.cancel()

            assertEquals(2, result.size)
            assertTrue(result.last() is CountryDetailUiState.ShowError)
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

        val businessModel = CountryDetail(
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
            car = CountryDetail.Car(
                side = CAR_DRIVE_SIDE
            ),
            coatOfArms = CountryDetail.CoatOfArms(
                png = COAT_OF_ARMS_PNG,
                svg = COAT_OF_ARMS_SVG
            )
        )
    }
}