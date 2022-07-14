package com.example.weatherapp.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(
    val id: Long,
    val name: String,
    val region: String,
    val country: String,
    val lat:Double,
    val lon:Double,
    val url:String) :Parcelable {

    val nrc
        get() = "$name, $region, $country"

    val rc
    get() = "$region, $country"
}


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
    val country:String,
    val lat:Double,
    val lon:Double,
    val tz_id:String,
    val localtime_epoch:Long,
    val localtime:String
):Parcelable

@Parcelize
data class Current(
    val last_updated_epoch:Long,
    val last_updated:String,
    val temp_c:Double,
    val temp_f:Double,
    val is_Day:Double,
    val condition:Condition,
    val wind_mph: Double,
    val wind_kph: Double,
    val wind_degree: Int,
    val  wind_dir: String,
    val  pressure_mb: Double,
    val  pressure_in: Double,
    val  precip_mm: Double,
    val  precip_in: Double,
    val  humidity: Int,
    val  cloud: Int,
    val  feelslike_c: Double,
    val  feelslike_f: Double,
    val  vis_km: Double,
    val  vis_miles: Double,
    val  uv: Double,
    val  gust_mph: Double,
    val  gust_kph: Double
    ):Parcelable

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
    val epoch:String,
    val day:Day,
    val astro:Astro,
    val hour:Map<String,Any>
    ):Parcelable

@Parcelize
data class Day(
    val maxtemp_c: Double,
    val mintemp_c: Double,
    val maxwind_mph: Double,
    val maxwind_kph: Double,
    val avghumidity: Double,
    ):Parcelable

@Parcelize
data class Astro(
    val sunrise:String,
    val sunset:String
):Parcelable




