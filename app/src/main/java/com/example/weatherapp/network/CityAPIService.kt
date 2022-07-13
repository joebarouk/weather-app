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

interface CityAPIService {

    @GET("search.json") //search.json is the path or endpoint that the method uses
    //when we call getProperties, retrofit append the endpoint search.json to the BASE_URL
    suspend fun getCities(@Query("filter") q: String,@Query("filter") token: String = TOKEN): List<City>

    @GET("forecast.json")
    suspend fun getWeather(@Query("filter") type: String): List<Weather>

}