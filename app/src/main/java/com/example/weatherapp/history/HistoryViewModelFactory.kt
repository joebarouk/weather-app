package com.example.weatherapp.history

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.database.History
import com.example.weatherapp.database.WeatherDatabaseDao
import com.example.weatherapp.network.City
import com.example.weatherapp.history.HistoryViewModel

class HistoryViewModelFactory(
    private val city: City,
    private val dataSource: WeatherDatabaseDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(city, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}