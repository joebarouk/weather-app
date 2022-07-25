package com.example.weatherapp.CityRepository

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.network.City

interface MyRepository {

    val cities: MutableLiveData<List<City>>
    suspend fun refreshCities(filter:String)
}
