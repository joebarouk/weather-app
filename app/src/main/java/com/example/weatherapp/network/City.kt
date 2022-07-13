package com.example.weatherapp.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(
    val id: Int,
    val name: String,
    val region: String,
    val country: String,
    val lat:Int,
    val long:Int,
    val url:String) :Parcelable {

    val what_city
        get() = name
}