package com.example.weatherapp.city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.network.City
import com.example.weatherapp.network.CityApi
import kotlinx.coroutines.launch

enum class CityApiStatus { LOADING, ERROR, DONE }

class CityViewModel : ViewModel() {


    private val _status = MutableLiveData<CityApiStatus>()
    val status: LiveData<CityApiStatus>
        get() = _status

    private val _cities = MutableLiveData<List<City>>()
    val cities: LiveData<List<City>>
        get() = _cities

    private val _navigateToSelectedCity = MutableLiveData<City?>()
    val navigateToSelectedCity: LiveData<City?>
        get() = _navigateToSelectedCity

    

     fun getCities(filter:String){
        viewModelScope.launch {
            _status.value = CityApiStatus.LOADING
            try {
                _cities.value = CityApi.retrofitService.getCities(filter)
                _status.value = CityApiStatus.DONE
            } catch (e: Exception) {
                _status.value = CityApiStatus.ERROR
                _cities.value = ArrayList()
            }
        }

    }

    fun displayCityDetails(city: City) {
        _navigateToSelectedCity.value = city
    }


    fun displayCityDetailsComplete() {
        _navigateToSelectedCity.value = null
    }


}