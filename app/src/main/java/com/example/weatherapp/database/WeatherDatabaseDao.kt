package com.example.weatherapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.weatherapp.network.Weather

@Dao
interface WeatherDatabaseDao {

    @Insert
    suspend fun insert(history: History)

    @Update
    suspend fun update(history: History)

    @Query("SELECT * from history_table WHERE dayId = :key")
    suspend fun get(key: Long): History?

    @Query("DELETE FROM history_table")
    suspend fun clear()


    @Query("SELECT * FROM history_table ORDER BY dayId DESC")
    fun getAllDays(): LiveData<List<History>>

    @Query("SELECT * FROM history_table ORDER BY dayId DESC LIMIT 1")
    suspend fun getToday(): History?

    @Query("SELECT * from history_table WHERE dayId = :key")
    fun getDayWithId(key: Long): LiveData<History>
}

