package com.john.countrylist.ui.states

import com.john.countrylist.domain.models.Country

internal sealed interface CountryListUiState {
    data object Idle : CountryListUiState
    data object Loading : CountryListUiState
    data class ShowCountryList(val data: List<Country>) : CountryListUiState
    data object ShowError : CountryListUiState
}