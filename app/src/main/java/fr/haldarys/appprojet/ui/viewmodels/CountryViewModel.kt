package fr.haldarys.appprojet.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.haldarys.appprojet.PlanYourHolidaysApplication
import fr.haldarys.appprojet.data.repositories.CountryRepository
import fr.haldarys.appprojet.data.states.CountryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(private val countryRepository: CountryRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(listOf<CountryUiState>())

    val uiState : StateFlow<List<CountryUiState>> = _uiState.asStateFlow()

    private fun getCountries(){
        viewModelScope.launch {
            countryRepository.getCountries().collect { list ->
                val listUi = list.map { model ->
                    CountryUiState(model.name, model.iso2, model.long, model.lat)
                }
                _uiState.emit(listUi)
            }
        }
    }

    init {
        getCountries()
    }
}