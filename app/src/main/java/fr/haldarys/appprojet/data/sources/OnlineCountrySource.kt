package fr.haldarys.appprojet.data.sources

import androidx.room.ColumnInfo
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.haldarys.appprojet.data.models.CountryModel
import fr.haldarys.appprojet.data.models.LocationModel
import fr.haldarys.appprojet.data.repositories.CountrySource
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

object OnlineCountrySource {
    private val interceptor = HttpLoggingInterceptor()
    init{
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient().newBuilder().addInterceptor(interceptor).build()


    private const val BASE_URL = "https://countriesnow.space"


    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)
        .build()

    data class Countries(
        val data : List<CountryModel>
    )

    interface CountryService {
        @GET("api/v0.1/countries/positions")
        suspend fun getCountries() : Countries
    }

    private val retrofitCountryService : CountryService by lazy{
        retrofit.create(CountryService::class.java)
    }

    suspend fun getCountries(): List<CountryModel> {
        return retrofitCountryService.getCountries().data
    }
}

