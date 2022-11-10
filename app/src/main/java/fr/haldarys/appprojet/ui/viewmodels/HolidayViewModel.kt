package fr.haldarys.appprojet.ui.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import fr.haldarys.appprojet.data.states.HolidayUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime
import java.util.*

class HolidayViewModel {
    @RequiresApi(Build.VERSION_CODES.O)
    private val _uiState = MutableStateFlow(HolidayUiState())

    @RequiresApi(Build.VERSION_CODES.O)
    val uiState: StateFlow<HolidayUiState> = _uiState.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun changeName(name: String){
        //Stuff to save in data source
        _uiState.value = HolidayUiState(
            name = name,
            location = uiState.value.location,
            date = uiState.value.date
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun changeLocation(location: String){
        //Stuff to save in data source
        _uiState.value = HolidayUiState(
            name = uiState.value.name,
            location = location,
            date = uiState.value.date
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun changeDate(date: String){
        //Stuff to save in data source
        _uiState.value = HolidayUiState(
            name = uiState.value.name,
            location = uiState.value.name,
            date = date
        )
    }
}