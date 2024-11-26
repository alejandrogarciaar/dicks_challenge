package com.john.countrylist.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.john.countrylist.domain.repository.CountryListRepository
import com.john.countrylist.ui.states.CountryListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
internal class CountryListViewModel @Inject constructor(
    private val repository: CountryListRepository,
    private val coroutineDispatcher: CoroutineContext
) : ViewModel() {

    private val _uiState: MutableStateFlow<CountryListUiState> =
        MutableStateFlow(CountryListUiState.Idle)

    val uiState: StateFlow<CountryListUiState> = _uiState.asStateFlow()

    fun getCountryList() {
        repository.getCountries()
            .onStart { _uiState.update { CountryListUiState.Loading } }
            .onEach { countries -> _uiState.update { CountryListUiState.ShowCountryList(data = countries) } }
            .catch { _uiState.update { CountryListUiState.ShowError } }
            .flowOn(coroutineDispatcher)
            .launchIn(viewModelScope)
    }

    fun getCountriesByQuery(query: String) {
        repository.getCountriesByQuery(query)
            .onStart { _uiState.update { CountryListUiState.Loading } }
            .onEach { countries -> _uiState.update { CountryListUiState.ShowCountryList(data = countries) } }
            .catch { _uiState.update { CountryListUiState.ShowError } }
            .flowOn(coroutineDispatcher)
            .launchIn(viewModelScope)
    }
}