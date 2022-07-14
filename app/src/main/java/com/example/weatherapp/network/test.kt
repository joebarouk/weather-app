package com.example.weatherapp.network

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.Query
import com.example.weatherapp.network.CityApiService
import com.example.weatherapp.network.CityApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.*

suspend fun main(){
print("hi")
  val job: Job =  GlobalScope.launch{
     print(CityApi.retrofitService.getCities("lon"))
        print("bye")
    }
job.join()
}