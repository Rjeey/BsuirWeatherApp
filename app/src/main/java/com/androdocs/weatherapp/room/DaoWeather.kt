package com.androdocs.weatherapp.room

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androdocs.weatherapp.model.WeatherData

interface DaoWeather {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertData(weatherData: WeatherData)
//
//
//    @Query("SELECT * FROM WeatherData WHERE UPDATE_AT =:update_at")
//    fun getCurrentWeatherData(update_at:String):WeatherData
//
//    @Query("SELECT * FROM WeatherData WHERE U =:u" )
//    fun getWeatherCurrentWeek()

}