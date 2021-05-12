package com.androdocs.weatherapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("/data/2.5/weather?")
    fun getCurrentWeatherData(@Query("q") q:String,
                              @Query("units") units:String,
                              @Query("appid") appid:String)

    companion object Factory{
        fun create(): WeatherService{

            var retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.openweathermap.org")
                .build()

            return retrofit.create(WeatherService::class.java)
        }
    }



}