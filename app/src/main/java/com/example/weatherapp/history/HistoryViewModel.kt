package com.example.weatherapp.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.weatherapp.database.History
import com.example.weatherapp.database.WeatherDatabaseDao
import com.example.weatherapp.network.City
import com.example.weatherapp.network.CityApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

class HistoryViewModel(city: City, private val database:WeatherDatabaseDao) : ViewModel() {

   suspend fun get_history_network(filter:String) {
       withContext(Dispatchers.IO) {
           clear()
           for (i in -6..0) {
               val dateFormat = SimpleDateFormat("yyyy-MM-dd");
               val cal = Calendar.getInstance();
               cal.add(Calendar.DATE, i);
               val date_string = dateFormat.format(cal.getTime())

               val history = CityApi.retrofitService.getHistory(filter, date_string)

              val date = history.forecast.forecastday[0].date
              val name = history.location.name
               val url = history.current.condition.icon
               val maxtemp_c = history.forecast.forecastday[0].day.max_temp
              val mintemp_c = history.forecast.forecastday[0].day.min_temp

                insert(History(date,name,url,maxtemp_c,mintemp_c))

           }
       }
   }




    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    private suspend fun update(history: History) {
        withContext(Dispatchers.IO) {
            database.update(history)
        }
    }

    private suspend fun insert(history: History) {
        withContext(Dispatchers.IO) {
            database.insert(history)
        }
    }


}