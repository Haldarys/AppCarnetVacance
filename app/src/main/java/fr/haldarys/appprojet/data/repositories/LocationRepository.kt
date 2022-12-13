package fr.haldarys.appprojet.data.repositories

import android.util.Log
import fr.haldarys.appprojet.data.models.LocationModel
import javax.inject.Inject
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import fr.haldarys.appprojet.PlanYourHolidaysApplication

interface LocationSource{

    suspend fun GetLocation(latvalue: String,lonvalue: String): LocationModel
}

interface LocationRepository{
    suspend fun getLocation(latvalue: String, lonvalue: String):LocationModel
    fun getLocationFromCache():LocationModel
}

class DefaultLocationRepository @Inject constructor(): LocationRepository{

    private lateinit var locationSource: LocationSource

    init {
        val appContext = PlanYourHolidaysApplication.getContext()
        val utilitiesEntryPoint =
            appContext?.let {
                EntryPointAccessors.fromApplication(
                    it, DefaultLocationRepoEntryPoint::class.java)
            }
        locationSource = utilitiesEntryPoint?.locationSource!!
    }

    override suspend fun getLocation(latvalue: String, lonvalue: String): LocationModel {
        Log.d("app","DefaultLocationRepository")
        return locationSource.GetLocation(latvalue,lonvalue)
    }

    override fun getLocationFromCache(): LocationModel {
        TODO("Not yet implemented")
    }
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DefaultLocationRepoEntryPoint {
    var locationSource: LocationSource
}

@InstallIn(SingletonComponent::class)
@Module
object LocationRepositoryModule {
    @Singleton
    @Provides
    fun provideLocationRepo(): LocationRepository {
        return DefaultLocationRepository()
    }
}