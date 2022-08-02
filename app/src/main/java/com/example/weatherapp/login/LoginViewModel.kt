package com.example.weatherapp.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.firebase.PushNotification
import com.example.weatherapp.firebase.RetrofitInstance
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.google.gson.Gson


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


    fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.postNotification(notification)
            println("joeeee $response")
            if(response.isSuccessful) {
                Log.d("MainActivity", "Response: ${Gson().toJson(response)}")
                println("hiiiiiiii")
            } else {
                Log.e("MainActivity", response.errorBody().toString())
                println("byeeee ${response.errorBody().toString()}")
            }
        } catch(e: Exception) {
            Log.e("MainActivity", e.toString())
            println("errooooor")
        }
    }
}