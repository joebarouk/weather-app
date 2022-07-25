package com.example.weatherapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.network.Current
import com.example.weatherapp.network.Forecast
import com.example.weatherapp.network.Location

@Entity(tableName = "history_table")
data class History(
    @PrimaryKey(autoGenerate = true)
    var dayId: Long = 0L,
    val location: Location,
    val current: Current,
    val forecast: Forecast
)
