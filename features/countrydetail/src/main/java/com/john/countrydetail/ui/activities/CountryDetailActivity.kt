package com.john.countrydetail.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
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
        setListeners()
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
                with(binding) {
                    progressCircular.isVisible = false
                    imageViewClose.isVisible = true
                }

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

    private fun setListeners() {
        binding.imageViewClose.setOnClickListener { finish() }
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
        with(binding) {
            carSideTextView.text = getString(R.string.country_detail_car_drive_side)
            carSideValueTextView.text = car.side
        }
    }

    private fun renderPopulation(population: Long) {
        with(binding) {
            populationTextView.text = getString(R.string.country_detail_population)
            populationValueTextView.text = population.toString()
        }
    }

    private fun renderRegion(region: String?) {
        with(binding) {
            regionTextView.text = getString(R.string.country_detail_region)
            regionValueTextView.text = region
        }
    }

    private fun renderSubregion(subregion: String?) {
        with(binding) {
            subregionTextView.text = getString(R.string.country_detail_subregion)
            subregionValueTextView.text = subregion
        }
    }

    private fun renderCapital(capital: String) {
        with(binding) {
            capitalTextView.text = getString(R.string.country_detail_capital)
            capitalValueTextView.text = capital
        }
    }

    private fun renderCurrencies(currencies: Map<String, CountryDetail.Currency>) {
        val currency = currencies.values.joinToString { "${it.symbol.uppercase()} (${it.name})" }
        with(binding) {
            currenciesTextView.text = getString(R.string.country_detail_currencies)
            currenciesValueTextView.text = currency
        }
    }

    private fun renderLanguages(languages: Map<String, String>) {
        val languagesText = languages.values.joinToString(separator = ",")
        with(binding) {
            languagesTextView.text = getString(R.string.country_detail_languages)
            languagesValueTextView.text = languagesText
        }
    }

    private fun renderCoatOfArms(coatOfArms: CountryDetail.CoatOfArms) {
        if (coatOfArms.png.isNotEmpty()) {
            with(binding) {
                coatOfArmsTextView.text = getString(R.string.country_detail_coat_at_arms)
                Glide.with(this@CountryDetailActivity).load(coatOfArms.png)
                    .into(coatOfArmsImageView)
            }
        }
    }

    companion object {
        const val COUNTRY_NAME_KEY = "COUNTRY_NAME_KEY"
    }
}