package com.example.weatherapp
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import com.example.weatherapp.network.CityApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main()= runBlocking {
   val job: Job = GlobalScope.launch {
        println(CityApi.retrofitService.getWeather("london").location.name)

    }
    job.join()

}


