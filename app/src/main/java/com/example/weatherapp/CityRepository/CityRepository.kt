package com.example.weatherapp.CityRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.network.City
import com.example.weatherapp.network.CityApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CityRepository(){

    val cities = MutableLiveData<List<City>>()

    suspend fun refreshVideos(filter:String) {
        withContext(Dispatchers.IO) {
            cities.postValue(CityApi.retrofitService.getCities(filter))
        }
    }
}