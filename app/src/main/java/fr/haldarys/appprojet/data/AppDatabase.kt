package fr.haldarys.appprojet.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.haldarys.appprojet.PlanYourHolidaysApplication
import fr.haldarys.appprojet.data.models.CountryModel
import fr.haldarys.appprojet.data.models.LocationModel
import fr.haldarys.appprojet.data.repositories.LocationSource
import fr.haldarys.appprojet.data.sources.CountryDao
import fr.haldarys.appprojet.data.sources.LocationCacheSource
import fr.haldarys.appprojet.data.sources.LocationDao
import javax.inject.Singleton

@Database(entities = [LocationModel::class, CountryModel::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
    abstract fun countryDao(): CountryDao

    companion object{
        @Volatile
       private var INSTANCE : AppDatabase? = null

       fun getDatabase(context : Context): AppDatabase{
           return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "PlanYourHolidays_database").build()
                     INSTANCE = instance
               instance
           }
       }
    }
}

@InstallIn(SingletonComponent::class)
@Module
object AppDatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase() : AppDatabase {
        return AppDatabase.getDatabase(PlanYourHolidaysApplication.getContext()!!);
    }
}