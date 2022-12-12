package fr.haldarys.appprojet.data.models

data class LocationModel (
    var house_number: Int,
    var city: String = "",
    var road: String = "",
    var state: String = "",
    var region: String = "",
    var postCode: String = "",
    var country: String = ""
)