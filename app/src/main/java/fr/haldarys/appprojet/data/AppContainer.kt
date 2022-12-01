package fr.haldarys.appprojet.data

import fr.haldarys.appprojet.data.repositories.DefaultLocationRepository
import fr.haldarys.appprojet.data.repositories.LocationRepository
import fr.haldarys.appprojet.data.repositories.LocationSource
import fr.haldarys.appprojet.data.sources.OnlineLocationSource

interface AppContainer {
    val locationSource: LocationSource
    val locationRepository : LocationRepository
}

class DefaultAppContainer: AppContainer{
    override val locationSource: LocationSource by lazy{
        OnlineLocationSource
    }

    override val locationRepository: LocationRepository by lazy{
        DefaultLocationRepository()
    }


}