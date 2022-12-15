package fr.haldarys.appprojet.data.repositories

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import fr.haldarys.appprojet.PlanYourHolidaysApplication
import fr.haldarys.appprojet.data.models.CountryModel
import fr.haldarys.appprojet.data.sources.CountryCacheSource
import kotlinx.coroutines.flow.Flow

interface CountrySource{

    suspend fun GetCountries(): Flow<List<CountryModel>>
}

interface CountryRepository{
    suspend fun getCountries(): Flow<List<CountryModel>>
}

class DefaultCountryRepository @Inject constructor():CountryRepository{

    private lateinit var countrySource: CountrySource

    init {
        val appContext = PlanYourHolidaysApplication.getContext()
        val utilitiesEntryPoint =
            appContext?.let {
                EntryPointAccessors.fromApplication(
                    it, DefaultCountryRepoEntryPoint::class.java)
            }
        countrySource = utilitiesEntryPoint?.countrySource!!
    }

    override suspend fun getCountries():Flow<List<CountryModel>> {
        return countrySource.GetCountries()
    }
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DefaultCountryRepoEntryPoint {
    var countrySource:CountrySource
}

@InstallIn(SingletonComponent::class)
@Module
object CountryRepositoryModule {
    @Singleton
    @Provides
    fun provideCountryRepo(): CountryRepository {
        return DefaultCountryRepository()
    }
}

@InstallIn(SingletonComponent::class)
@Module
object CountrySourceModule {
    @Provides
    @Singleton
    fun provideCountrySource() : CountrySource {
        return CountryCacheSource;
    }
}