package com.example.weatherapp.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class LoginViewModel : ViewModel() {

    private val _navigateToPage = MutableLiveData<Boolean>()
    val navigateToPage: LiveData<Boolean>
        get() = _navigateToPage


    fun displayPageDetails() {
        _navigateToPage.value = true
    }


    fun displayPageDetailsComplete() {
        _navigateToPage.value = false
    }
}