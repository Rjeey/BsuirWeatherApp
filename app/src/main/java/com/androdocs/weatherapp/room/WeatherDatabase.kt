//package com.androdocs.weatherapp.room
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.androdocs.weatherapp.model.WeatherData
//
//@Database(entities = arrayOf(WeatherData::class), version = 1, exportSchema = false)
//abstract class WeatherDatabase : RoomDatabase() {
//
//    abstract fun loginDao(): DaoWeather
//
//    companion object {
//
//        @Volatile
//        private var INSTANCE: WeatherDatabase? = null
//
//        fun getDataseClient(context: Context): WeatherDatabase {
//
//            if (INSTANCE != null) return INSTANCE!!
//
//            synchronized(this) {
//
//                INSTANCE = Room
//                    .databaseBuilder(context, WeatherDatabase::class.java, "WEATHER_DATABASE")
//                    .fallbackToDestructiveMigration()
//                    .build()
//
//                return INSTANCE!!
//
//            }
//        }
//
//    }
//}