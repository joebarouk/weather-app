package com.example.weatherapp.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.weatherapp.network.Weather

@Dao
interface WeatherDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(history: History)

    @Update
    suspend fun update(history: History)

    @Query("SELECT * from history_table WHERE name = :key")
    suspend fun get(key: String): History?

    @Query("DELETE FROM history_table")
    suspend fun clear()

    @Query("DELETE FROM history_table WHERE name = :key")
    suspend fun clear_element(key:String)

    @Query("SELECT * FROM history_table WHERE name = :key ORDER BY date DESC")
    fun getAllDays(key: String): LiveData<List<History>>

    @Query("SELECT * FROM history_table ORDER BY date DESC LIMIT 1")
    suspend fun getToday(): History?

    @Query("SELECT * from history_table WHERE date = :key")
    fun getDayWithId(key: String): LiveData<History>
}

