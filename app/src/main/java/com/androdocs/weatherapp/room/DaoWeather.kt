package com.androdocs.weatherapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androdocs.weatherapp.model.WeatherData

@Dao
interface DaoWeather {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(weatherData:WeatherData)


    @Query("SELECT * FROM WeatherData WHERE UPDATE_AT =:update_at")
    fun getCurrentWeatherData(update_at:String):WeatherData

    fun getWeatherCurrentWeek()

}