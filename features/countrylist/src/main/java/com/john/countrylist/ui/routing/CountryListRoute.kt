package com.john.countrylist.ui.routing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.john.countrylist.ui.actions.CountryListScreenActions
import com.john.countrylist.ui.screens.CountryListRetryScreen
import com.john.countrylist.ui.screens.CountryListScreen
import com.john.countrylist.ui.screens.CountryListSearchScreen
import com.john.countrylist.ui.states.CountryListUiState
import com.john.countrylist.ui.viewModel.CountryListViewModel

@Composable
internal fun CountryListRoute(
    viewModel: CountryListViewModel,
    actions: CountryListScreenActions
) {
    Scaffold(
        topBar = {
            CountryListSearchScreen(
                onQueryChanged = { query -> actions.onQueryChanged.invoke(query) },
                onRetry = { actions.onRetryClicked.invoke() }
            )
        },
        content = { paddingValues ->
            val uiState: CountryListUiState by viewModel.uiState.collectAsStateWithLifecycle()
            when (uiState) {
                CountryListUiState.Loading -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier,
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }
                }

                is CountryListUiState.ShowCountryList -> {
                    CountryListScreen(
                        paddingValues = paddingValues,
                        items = (uiState as CountryListUiState.ShowCountryList).data,
                        actions = actions
                    )
                }

                CountryListUiState.ShowError -> {
                    CountryListRetryScreen(
                        onRetryClicked = actions.onRetryClicked
                    )
                }

                else -> Unit
            }
        }
    )
}