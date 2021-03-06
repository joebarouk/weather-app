package com.example.weatherapp.network

import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.widget.ImageView
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    val location:Location,
    val current:Current,
    val forecast:Forecast
):Parcelable

@Parcelize
data class Location(
    val name:String,
    val region:String,
    val country:String
):Parcelable

@Parcelize
data class Current(
    val last_updated:String,
    val temp_c:Double,
    val condition:Condition,
    val wind_kph: Double,
    val  pressure_mb: Double,
    val  humidity: Int,
):Parcelable{
    val updated
    get() = "Updated at: $last_updated"

    val temp
    get() = "${temp_c}°C"

    val wind
    get() = "$wind_kph kph"

    val pressure
    get() = "$pressure_mb mb"

    val humidity_string
    get() = "${humidity}%"

}

@Parcelize
data class Condition(
    val text:String,
    val icon:String,
    val code:Int
):Parcelable

@Parcelize
data class Forecast(
    val forecastday:List<Forecasts>
):Parcelable

@Parcelize
data class Forecasts(
    val date:String,
    val day:Day,
    val astro:Astro,
):Parcelable

@Parcelize
data class Day(
    val maxtemp_c: Double,
    val mintemp_c: Double,
):Parcelable{
    val min_temp
    get() = "Min Temp: ${mintemp_c}°C"

    val max_temp
    get() = "Max Temp: ${maxtemp_c}°C"
}

@Parcelize
data class Astro(
    val sunrise:String,
    val sunset:String
):Parcelable{
}

@Parcelize
data class Grid(
    val property:String,
    val property_detail:String?
):Parcelable

