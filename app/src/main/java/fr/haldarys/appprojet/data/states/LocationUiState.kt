package fr.haldarys.appprojet.data.states

data class LocationUiState (
        var roadNumber: Int,
        var city: String = "",
        var road: String = "",
        var state: String = "",
        var postCode: String = "",
        var country: String = ""
)

