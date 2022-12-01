package fr.haldarys.appprojet.data.repositories

import android.location.Location
import android.os.Debug
import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.haldarys.appprojet.data.models.LocationModel
import javax.inject.Inject

interface LocationSource{

    suspend fun GetLocation(latvalue: String,lonvalue: String): LocationModel
}

interface LocationRepository{
    suspend fun getLocation(latvalue: String, lonvalue: String):LocationModel
    fun getLocationFromCache():LocationModel
}

class DefaultLocationRepository() : LocationRepository{
    @Inject
    lateinit var locationSource: LocationSource

    override suspend fun getLocation(latvalue: String, lonvalue: String): LocationModel {
        Log.d("app","DefaultLocationRepository")
        return locationSource.GetLocation(latvalue,lonvalue)
    }

    override fun getLocationFromCache(): LocationModel {
        TODO("Not yet implemented")
    }
}

@InstallIn(SingletonComponent::class)
@Module
object GameRepositoryModule {
    @Provides
    fun provideLocationRepo(): LocationRepository{
        return DefaultLocationRepository()
    }
}