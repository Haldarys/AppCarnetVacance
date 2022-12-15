package fr.haldarys.appprojet.data.sources

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import fr.haldarys.appprojet.PlanYourHolidaysApplication
import fr.haldarys.appprojet.data.AppDatabase
import fr.haldarys.appprojet.data.models.CountryModel
import fr.haldarys.appprojet.data.repositories.CountrySource
import fr.haldarys.appprojet.work.RefreshCountriesWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.time.Instant
import java.util.Date
import java.util.concurrent.TimeUnit

@Dao
interface CountryDao{
    @Query("SELECT * FROM countries")
    fun getAllCountries() : Flow<List<CountryModel>>

    @Query("SELECT * FROM countries WHERE name =:countryName")
    fun getByName(countryName:String) : Flow<CountryModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(country : List<CountryModel>)
}


object CountryCacheSource : CountrySource{
        private lateinit var appDatabase: AppDatabase

        @RequiresApi(Build.VERSION_CODES.O)
        private var lastUpdateTime : Instant = Instant.now()

        init {
            val appContext = PlanYourHolidaysApplication.getContext()
            val utilitiesEntryPoint =
                appContext?.let {
                    EntryPointAccessors.fromApplication(
                        it, DefaultCacheCountrySourceEntryPoint::class.java)
                }
            appDatabase = utilitiesEntryPoint?.appDatabase!!

            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresBatteryNotLow(true)
                .build()
            //Launch a periodic work to update the cache
            val repeatingRequest = PeriodicWorkRequestBuilder<RefreshCountriesWorker>(1,TimeUnit.DAYS)
                .setConstraints(constraints)
                .build()



            WorkManager.getInstance(PlanYourHolidaysApplication.getContext()!!)
                .enqueue(repeatingRequest)



        }

    suspend fun refreshCountries(){
        withContext(Dispatchers.IO){
            val countries = OnlineCountrySource.getCountries()
            appDatabase.countryDao().insert(countries)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun GetCountries(): Flow<List<CountryModel>> {
        refreshCountries()
        return appDatabase.countryDao().getAllCountries()
    }
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DefaultCacheCountrySourceEntryPoint {
    var appDatabase: AppDatabase
}