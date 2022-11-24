package fr.haldarys.appprojet.data.repositories

import android.location.Location
import android.os.Debug
import android.util.Log
import fr.haldarys.appprojet.data.models.LocationModel

interface LocationSource{

    suspend fun GetLocation(latvalue: String,lonvalue: String): LocationModel
}

interface LocationRepository{
    suspend fun getLocation(latvalue: String, lonvalue: String):LocationModel
    fun getLocationFromCache():LocationModel
}

class DefaultLocationRepository(val locationSource: LocationSource) : LocationRepository{
    override suspend fun getLocation(latvalue: String, lonvalue: String): LocationModel {
        Log.d("app","DefaultLocationRepository")
        return locationSource.GetLocation(latvalue,lonvalue)
    }

    override fun getLocationFromCache(): LocationModel {
        TODO("Not yet implemented")
    }
}