package com.example.weatherapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.network.Current
import com.example.weatherapp.network.Forecast
import com.example.weatherapp.network.Location

@Entity(tableName = "history_table")
data class History(
    @PrimaryKey
    val date:String,
    val name:String,
    val url: String,
    val maxtemp_c: String,
    val mintemp_c: String,
)
