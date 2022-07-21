package com.example.weatherapp.weather

import android.app.Application
import android.content.Context
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.lifecycle.*
import androidx.navigation.common.R
import com.example.weatherapp.city.CityApiStatus
import com.example.weatherapp.network.City
import com.example.weatherapp.network.CityApi
import com.example.weatherapp.network.Grid
import com.example.weatherapp.network.Weather
import kotlinx.coroutines.*
import java.security.AccessController.getContext

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

   private val _weather_properties = MutableLiveData<List<Grid>>()
    val weather_properties: LiveData<List<Grid>>
    get() = _weather_properties




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
                add()

            } catch (e: Exception) {
                _status.value = CityApiStatus.ERROR
            }

        }

    }
   private fun add(){
       val weather_list = arrayListOf<Grid>()
       weather_list.add(Grid("Sunrise", weather.value?.forecast?.forecastday?.get(0)?.astro?.sunrise))
       weather_list.add(Grid("Sunset", weather.value?.forecast?.forecastday?.get(0)?.astro?.sunset))
       weather_list.add(Grid("Wind",weather.value?.current?.wind))
       weather_list.add(Grid("Pressure",weather.value?.current?.pressure))
       weather_list.add(Grid("Humidity",weather.value?.current?.humidity_string))
       weather_list.add(Grid("Created By","Weather Api"))
        _weather_properties.value = weather_list
    }



}