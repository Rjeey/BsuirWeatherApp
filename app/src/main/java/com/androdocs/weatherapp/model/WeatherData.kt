package com.androdocs.weatherapp.model

import androidx.room.Entity

//@Entity
data class WeatherData(val address: String,
                       val update_at: String,
                       val status: String,
                       val temp: String,
                       val temp_min: String,
                       val temp_max: String,
                       val sunrise: String,
                       val sunset: String,
                       val wind: String,
                       val pressures: String,
                       val humidity: String)