package fr.haldarys.appprojet.data.sources

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import fr.haldarys.appprojet.PlanYourHolidaysApplication
import fr.haldarys.appprojet.data.AppDatabase
import fr.haldarys.appprojet.data.models.LocationModel
import fr.haldarys.appprojet.data.repositories.DefaultLocationRepoEntryPoint
import fr.haldarys.appprojet.data.repositories.LocationSource
import java.time.Instant
import java.util.Date

@Dao
interface LocationDao{
    @Query("SELECT * FROM locations")
    fun getAllLocations() : List<LocationModel>

    @Query("SELECT * FROM locations WHERE city = :locationCity")
    fun getByCity(locationCity:String):List<LocationModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(location : LocationModel?)
}


object LocationCacheSource : LocationSource{
        private lateinit var appDatabase: AppDatabase

        @RequiresApi(Build.VERSION_CODES.O)
        private var lastUpdateTime : Instant = Instant.now()

        init {
            val appContext = PlanYourHolidaysApplication.getContext()
            val utilitiesEntryPoint =
                appContext?.let {
                    EntryPointAccessors.fromApplication(
                        it, DefaultCacheLocationSourceEntryPoint::class.java)
                }
            appDatabase = utilitiesEntryPoint?.appDatabase!!
        }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun GetLocations(): List<LocationModel> {

        if (Instant.now().epochSecond - lastUpdateTime.epochSecond > 3600){
            appDatabase.locationDao().getAllLocations().map {
                appDatabase.locationDao().insert(it)
            }
        }
        return appDatabase.locationDao().getAllLocations()
    }

    override suspend fun GetLocation(latvalue: String, lonvalue: String): LocationModel {
        return OnlineLocationSource.GetLocation(latvalue,lonvalue)
    }
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DefaultCacheLocationSourceEntryPoint {
    var appDatabase: AppDatabase
}