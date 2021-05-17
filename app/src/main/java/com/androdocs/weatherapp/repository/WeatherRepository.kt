package com.androdocs.weatherapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.androdocs.weatherapp.model.WeatherData
//import com.androdocs.weatherapp.room.WeatherDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class WeatherRepository {

//    companion object {
//
//        var database: WeatherDatabase? = null
//
//        var model: LiveData<WeatherData>? = null
//
//        fun initializeDB(context: Context) : WeatherDatabase {
//            return WeatherDatabase.getDataseClient(context)
//        }
//
//        fun insertData(context: Context, username: String, password: String) {
//
//            database = initializeDB(context)
//
//            CoroutineScope(IO).launch {
////                database!!.loginDao().insertData(WeatherData(username, password))
//            }
//
//        }
//
//        fun getLoginDetails(context: Context, curr_date: String): WeatherData {
//
//            database = initializeDB(context)
//
//            return database!!.loginDao().getCurrentWeatherData(curr_date)
//        }
//
//    }
}