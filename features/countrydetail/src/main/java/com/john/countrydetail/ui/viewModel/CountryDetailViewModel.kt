package com.john.countrydetail.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.john.countrydetail.domain.repository.CountryDetailRepository
import com.john.countrydetail.ui.states.CountryDetailUiState
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

@HiltViewModel
internal class CountryDetailViewModel @Inject constructor(
    private val countryDetailRepository: CountryDetailRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<CountryDetailUiState> =
        MutableStateFlow(CountryDetailUiState.Idle)

    val uiState: StateFlow<CountryDetailUiState> = _uiState.asStateFlow()

    fun getCountryDetailByName(countryName: String) {
        countryDetailRepository.getCountryDetail(countryName)
            .onStart { _uiState.update { CountryDetailUiState.Loading } }
            .onEach { countryDetail -> _uiState.update { CountryDetailUiState.ShowDetail(data = countryDetail) } }
            .catch {
                _uiState.update { CountryDetailUiState.ShowError }
                println(it)
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }
}