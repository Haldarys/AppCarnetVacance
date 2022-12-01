package fr.haldarys.appprojet.data.sources

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.haldarys.appprojet.data.models.LocationModel
import fr.haldarys.appprojet.data.repositories.LocationSource
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

object OnlineLocationSource : LocationSource {

    private const val BASE_URL = "https://nominatim.openstreetmap.org"


    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    data class OSMLocations(
        @Json(name = "address")
        val location : LocationModel
    )

    data class OnlineLocationModel(
        @Json(name = "house_number")
        var roadNumber: Int,
        @Json(name = "city")
        var city: String,
        @Json(name = "road")
        var road: String ,
        @Json(name = "state")
        var state: String,
        @Json(name = "region")
        var region: String,
        @Json(name = "postcode")
        var postCode: String ,
        @Json(name = "country")
        var country: String
    )

    interface OSMLocationsService {
        @GET("reverse?format=jsonv2 & lat=<latvalue>&lon=<lonvalue>")
        fun GetLocation( @Query(value="latvalue") latvalue: String, @Query(value = "lonvalue") lonvalue : String) : OSMLocations
    }

    private val retrofitOSMLocationsService : OSMLocationsService by lazy{
        retrofit.create(OSMLocationsService::class.java)
    }

    override suspend fun GetLocation(latvalue: String,lonvalue: String): LocationModel {
        return retrofitOSMLocationsService.GetLocation(latvalue = latvalue,lonvalue =lonvalue ).location
    }
}

@InstallIn(SingletonComponent::class)
@Module
object LocationSourceModule {
    @Provides
    @Singleton
    fun provideLocationSource() : LocationSource {
        return OnlineLocationSource;
    }
}