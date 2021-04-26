package com.androdocs.weatherapp.service

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.androdocs.weatherapp.R
import com.androdocs.weatherapp.model.WeatherData
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class ServiceWeather(@field:SuppressLint("StaticFieldLeak") var view: View) : AsyncTask<String, Void, String>() {

    var city: String = "delhi,in"
    val api: String = "29ce07b6457ed3fa2eb37330c490be99" // Use your own API key

    override fun onPreExecute() {
        super.onPreExecute()

        view.findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
        view.findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE
        view.findViewById<TextView>(R.id.errorText).visibility = View.GONE
    }

    override fun doInBackground(vararg params: String?): String? {
        return try {
            URL("https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=$api")
                .readText(Charsets.UTF_8)
        } catch (e: Exception) {
            println(e.printStackTrace())
            return null
        }
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        try {
            val jsonObj = JSONObject(result)
            val main = jsonObj.getJSONObject("main")
            val sys = jsonObj.getJSONObject("sys")
            val wind = jsonObj.getJSONObject("wind")
            val weather = jsonObj.getJSONArray("weather").getJSONObject(0)

            val updatedAt: Long = jsonObj.getLong("dt")
            val updatedAtText = "Updated at: " + SimpleDateFormat(
                "dd/MM/yyyy hh:mm a",
                Locale.ENGLISH
            ).format(Date(updatedAt * 1000))
            val temp = main.getString("temp") + "°C"
            val tempMin = "Min Temp: " + main.getString("temp_min") + "°C"
            val tempMax = "Max Temp: " + main.getString("temp_max") + "°C"
            val pressure = main.getString("pressure")
            val humidity = main.getString("humidity")

            val sunrise: Long = sys.getLong("sunrise")
            val sunset: Long = sys.getLong("sunset")
            val windSpeed = wind.getString("speed")
            val weatherDescription = weather.getString("description")

            val address = jsonObj.getString("name") + ", " + sys.getString("country")


            view.findViewById<TextView>(R.id.address).text = address
            view.findViewById<TextView>(R.id.updated_at).text = updatedAtText
            view.findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
            view.findViewById<TextView>(R.id.temp).text = temp
            view.findViewById<TextView>(R.id.temp_min).text = tempMin
            view.findViewById<TextView>(R.id.temp_max).text = tempMax
            view.findViewById<TextView>(R.id.sunrise).text =
                SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise * 1000))
            view.findViewById<TextView>(R.id.sunset).text =
                SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset * 1000))
            view.findViewById<TextView>(R.id.wind).text = windSpeed
            view.findViewById<TextView>(R.id.pressure).text = pressure
            view.findViewById<TextView>(R.id.humidity).text = humidity

            view.findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
            view.findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE

        } catch (e: Exception) {
            view.findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
            view.findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
        }
    }
}