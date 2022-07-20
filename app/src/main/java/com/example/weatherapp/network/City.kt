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



