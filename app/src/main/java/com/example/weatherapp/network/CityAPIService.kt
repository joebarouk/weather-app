package com.example.weatherapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.weatherapi.com/v1/"
private const val TOKEN = "46c06661abc1409faff123206221307"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CityApiService {

    @GET("search.json") //search.json is the path or endpoint that the method uses
    //when we call getProperties, retrofit append the endpoint search.json to the BASE_URL
    suspend fun getCities(@Query("q") q: String,@Query("key") key: String = TOKEN): List<City>

    @GET("forecast.json")
    suspend fun getWeather(@Query("q") q: String,@Query("key") key: String = TOKEN,@Query("hour") hour:Int = 0): List<Weather>

}

object CityApi {
    val retrofitService : CityApiService by lazy { retrofit.create(CityApiService::class.java) }
}