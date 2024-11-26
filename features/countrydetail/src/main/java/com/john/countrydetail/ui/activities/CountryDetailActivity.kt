package com.john.countrydetail.ui.activities

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.john.countrydetail.R
import com.john.countrydetail.databinding.CountryDetailActivityBinding
import com.john.countrydetail.domain.models.CountryDetail
import com.john.countrydetail.ui.states.CountryDetailUiState
import com.john.countrydetail.ui.viewModel.CountryDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CountryDetailActivity : FragmentActivity() {

    private val viewModel: CountryDetailViewModel by viewModels()

    private var _binding: CountryDetailActivityBinding? = null
    private val binding: CountryDetailActivityBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = CountryDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindViewModel()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        viewModel.getCountryDetailByName(
            intent.getStringExtra(COUNTRY_NAME_KEY)
                ?: throw IllegalArgumentException("Country extra is required")
        )
    }

    private fun bindViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect(::observeUiState)
            }
        }
    }

    private fun observeUiState(state: CountryDetailUiState) {
        when (state) {
            CountryDetailUiState.Loading -> {
                binding.progressCircular.isVisible = true
            }

            is CountryDetailUiState.ShowDetail -> {
                binding.progressCircular.isVisible = false
                renderCountryDetail(state.data)
            }

            CountryDetailUiState.ShowError -> {
                binding.progressCircular.isVisible = false
            }

            else -> Unit
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun renderCountryDetail(data: CountryDetail) {
        renderFlag(data.flags.png)
        renderName(data.name)
        renderCapital(data.capital.first())
        renderRegion(data.region)
        renderSubregion(data.subregion)
        renderLanguages(data.languages)
        renderCurrencies(data.currencies)
        renderPopulation(data.population)
        renderCardDriveSide(data.car)
        renderCoatOfArms(data.coatOfArms)
    }

    private fun renderFlag(png: String) {
        Glide.with(this).load(png).into(binding.flagImageView)
    }

    private fun renderName(name: CountryDetail.Name) {
        with(binding) {
            commonNameTextView.text = name.common
            officialNameTextView.text = name.official
        }
    }

    private fun renderCardDriveSide(car: CountryDetail.Car) {
        val carSideText = getString(
            R.string.country_detail_item_placeholder,
            getString(R.string.country_detail_car_drive_side),
            car.side
        )
        renderHtml(binding.carSideTextView, carSideText)
    }

    private fun renderPopulation(population: Long) {
        val populationText = getString(
            R.string.country_detail_item_number_placeholder,
            getString(R.string.country_detail_population),
            population
        )
        renderHtml(binding.populationTextView, populationText)
    }

    private fun renderRegion(region: String?) {
        val regionText = getString(
            R.string.country_detail_item_placeholder,
            getString(R.string.country_detail_region),
            region
        )
        renderHtml(binding.regionTextView, regionText)
    }

    private fun renderSubregion(subregion: String?) {
        val subregionText = getString(
            R.string.country_detail_item_placeholder,
            getString(R.string.country_detail_subregion),
            subregion
        )
        renderHtml(binding.subregionTextView, subregionText)
    }

    private fun renderCapital(capital: String) {
        val capitalText = getString(
            R.string.country_detail_item_placeholder,
            getString(R.string.country_detail_capital),
            capital
        )
        renderHtml(binding.capitalTextView, capitalText)
    }

    private fun renderCurrencies(currencies: Map<String, CountryDetail.Currency>) {
        val currency = currencies.values.joinToString { "${it.symbol.uppercase()} (${it.name})" }
        val currenciesText = getString(
            R.string.country_detail_item_placeholder,
            getString(R.string.country_detail_currencies),
            currency
        )
        renderHtml(binding.currenciesTextView, currenciesText)
    }

    private fun renderLanguages(languages: Map<String, String>) {
        val languagesText = languages.values.joinToString(separator = ",")
        val languageString = getString(
            R.string.country_detail_item_placeholder,
            getString(R.string.country_detail_languages),
            languagesText
        )
        renderHtml(binding.languagesTextView, languageString)
    }

    private fun renderCoatOfArms(coatOfArms: CountryDetail.CoatOfArms) {
        if (coatOfArms.png.isNotEmpty()) {
            binding.coatOfArmsTextView.text = HtmlCompat.fromHtml(
                getString(R.string.country_detail_coat_at_arms),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
            Glide.with(this).load(coatOfArms.png).into(binding.coatOfArmsImageView)
        }
    }

    private fun renderHtml(textView: TextView, text: String) {
        textView.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    companion object {
        const val COUNTRY_NAME_KEY = "COUNTRY_NAME_KEY"
    }
}