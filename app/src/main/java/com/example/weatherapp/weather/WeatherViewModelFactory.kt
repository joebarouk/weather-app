package com.example.weatherapp.weather

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.network.City
import com.example.weatherapp.network.Weather

class WeatherViewModelFactory(
    private val city: City,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(city, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}