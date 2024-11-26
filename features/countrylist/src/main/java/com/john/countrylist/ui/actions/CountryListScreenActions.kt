package com.john.countrylist.ui.actions

import com.john.countrylist.domain.models.Country

internal data class CountryListScreenActions(
    val onQueryChanged: (query: String) -> Unit,
    val onCountryClicked: (country: Country) -> Unit,
    val onRetryClicked: () -> Unit
)