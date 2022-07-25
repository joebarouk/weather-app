package com.example.weatherapp.city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.CityRepository.CityRepository
import com.example.weatherapp.CityRepository.MyRepository
import com.example.weatherapp.network.City
import com.example.weatherapp.network.CityApi
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class CityApiStatus { LOADING, ERROR, DONE }
@HiltViewModel
class CityViewModel @Inject constructor(private val repository: MyRepository) : ViewModel() {


    private val _status = MutableLiveData<CityApiStatus>()
    val status: LiveData<CityApiStatus>
        get() = _status
/*
    private val _cities = MutableLiveData<List<City>>()
    val cities: LiveData<List<City>>
        get() = _cities

 */

    private val _navigateToSelectedCity = MutableLiveData<City?>()
    val navigateToSelectedCity: LiveData<City?>
        get() = _navigateToSelectedCity

   // private val cityRepository = CityRepository()

     fun getCities(filter:String){
        viewModelScope.launch {
            _status.value = CityApiStatus.LOADING
            try {
              //  _cities.value = CityApi.retrofitService.getCities(filter)
                repository.refreshCities(filter)
                _status.value = CityApiStatus.DONE
            } catch (e: Exception) {
                _status.value = CityApiStatus.ERROR
               // _cities.value = ArrayList()
            }
        }

    }

    val cities = repository.cities


    fun displayCityDetails(city: City) {
        _navigateToSelectedCity.value = city
    }


    fun displayCityDetailsComplete() {
        _navigateToSelectedCity.value = null
    }

}