package com.androdocs.weatherapp.service

import android.os.AsyncTask
import com.androdocs.weatherapp.model.WeatherData

class ServiceWeather: AsyncTask<String, Void, WeatherData>() {

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: String?): WeatherData {
        TODO("Not yet implemented")
    }

    override fun onProgressUpdate(vararg values: Void?) {
        super.onProgressUpdate(*values)
    }

    override fun onCancelled(result: WeatherData?) {
        super.onCancelled(result)
    }

    override fun onPostExecute(result: WeatherData?) {
        super.onPostExecute(result)
    }
}