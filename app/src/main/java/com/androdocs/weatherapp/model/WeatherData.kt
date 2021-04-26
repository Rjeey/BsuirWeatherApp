package com.androdocs.weatherapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WeatherData")
data class WeatherData(

    @ColumnInfo(name = "address")
    var address: String,
    @ColumnInfo(name = "update_at")
    var update_at: String,
    @ColumnInfo(name = "status")
    var status: String,
    @ColumnInfo(name = "temp")
    var temp: String,
    @ColumnInfo(name = "temp_min")
    var temp_min: String,
    @ColumnInfo(name = "temp_max")
    var temp_max: String,
    @ColumnInfo(name = "sunrise")
    var sunrise: String,
    @ColumnInfo(name = "sunset")
    var sunset: String,
    @ColumnInfo(name = "wind")
    var wind: String,
    @ColumnInfo(name = "pressures")
    var pressures: String,
    @ColumnInfo(name = "humidity")
    var humidity: String
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Long? = null
}
