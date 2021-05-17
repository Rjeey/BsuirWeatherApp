package com.androdocs.weatherapp.model.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("lat") val lat:Double,
    @SerializedName("lon") val lon:Double,
    @SerializedName("timezone") val timezone:String,
    @SerializedName("timezone_offset") val timezone_offset:Int,
    )