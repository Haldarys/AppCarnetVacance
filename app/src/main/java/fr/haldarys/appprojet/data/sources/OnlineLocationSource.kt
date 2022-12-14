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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

object OnlineLocationSource : LocationSource {
    private val interceptor = HttpLoggingInterceptor()
    init{
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient().newBuilder().addInterceptor(interceptor).build()


    private const val BASE_URL = "https://nominatim.openstreetmap.org"


    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)
        .build()

    data class OSMLocations(
        val address : LocationModel
    )

    data class OnlineLocationModel(
        var house_number: Int,
        var city: String,
        var road: String ,
        var state: String,
        var region: String,
        var postCode: String ,
        var country: String
    )

    interface OSMLocationsService {
        @GET("reverse")
        suspend fun GetLocation(@Query(value="format") format: String = "jsonv2", @Query(value="lat") latvalue: String, @Query(value = "lon") lonvalue : String) : OSMLocations
    }

    private val retrofitOSMLocationsService : OSMLocationsService by lazy{
        retrofit.create(OSMLocationsService::class.java)
    }

    override suspend fun GetLocation(latvalue: String,lonvalue: String): LocationModel {
        return retrofitOSMLocationsService.GetLocation(latvalue = latvalue,lonvalue =lonvalue ).address
    }
}

