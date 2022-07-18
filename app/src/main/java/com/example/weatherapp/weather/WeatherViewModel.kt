package com.example.weatherapp.weather

import android.app.Application
import androidx.lifecycle.*
import com.example.weatherapp.city.CityApiStatus
import com.example.weatherapp.network.City
import com.example.weatherapp.network.CityApi
import com.example.weatherapp.network.Weather
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class WeatherViewModel(city: City, app:Application) : AndroidViewModel(app) {

    private val _status = MutableLiveData<CityApiStatus>()
    val status: LiveData<CityApiStatus>
        get() = _status

    private val _selectedCity = MutableLiveData<City>()
    val selectedCity: LiveData<City>
        get() = _selectedCity

    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather>
        get() = _weather




    init {
       _selectedCity.value = city
        selectedCity.value?.let { getCityWeather(it.name) }


    }

    fun getCityWeather(filter:String){
        viewModelScope.launch {
            _status.value = CityApiStatus.LOADING
            try {
                _weather.value = CityApi.retrofitService.getWeather(filter)
                _status.value = CityApiStatus.DONE
            } catch (e: Exception) {
                _status.value = CityApiStatus.ERROR
                //_weather.value = ArrayList()
            }
        }
    }


}