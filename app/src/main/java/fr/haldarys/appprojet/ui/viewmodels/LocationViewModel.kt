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
import fr.haldarys.appprojet.data.repositories.DefaultLocationRepository
import fr.haldarys.appprojet.data.repositories.LocationRepository
import fr.haldarys.appprojet.data.states.LocationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val locationRepository: LocationRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(LocationUiState(0,"","","","",""))

    val uiState : StateFlow<LocationUiState> = _uiState.asStateFlow()

    private fun getLocation(){
        viewModelScope.launch {
            try{
                val location = locationRepository.getLocation(latvalue = "48.424730148122215",lonvalue = "-71.0687216220378")
                _uiState.emit(LocationUiState(roadNumber = location.house_number, city = location.city, road = location.road, country = location.country, state = location.state, postCode = location.postCode))
            }
            catch(e: Exception){
                Log.e("", e.message?:"not found")
            }
        }
    }

    init {
        getLocation()
    }
}