package com.john.countrydetail.ui.states

import com.john.countrydetail.domain.models.CountryDetail

internal sealed interface CountryDetailUiState {
    data object Idle : CountryDetailUiState
    data object Loading : CountryDetailUiState
    data class ShowDetail(val data: CountryDetail) : CountryDetailUiState
    data object ShowError : CountryDetailUiState
}
