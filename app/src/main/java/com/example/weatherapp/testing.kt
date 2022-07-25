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

/*
fun main()= runBlocking {
   val job: Job = GlobalScope.launch {
        println(CityApi.retrofitService.getWeather("london").location.name)

    }
    job.join()

}
 */

@RequiresApi(Build.VERSION_CODES.O)
fun main(){
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formatted = current.format(formatter)
    println("Current Date and Time is: $formatted")
}