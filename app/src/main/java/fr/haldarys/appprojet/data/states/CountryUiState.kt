package fr.haldarys.appprojet.data.states

import androidx.room.ColumnInfo

data class CountryUiState (
        var name : String,
        var iso2 : String,
        var long : String,
        var lat : String,
)

