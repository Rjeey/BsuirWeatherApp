package com.androdocs.weatherapp.onboarding.screen

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.androdocs.weatherapp.Constants
import com.androdocs.weatherapp.LocationFinder
import com.androdocs.weatherapp.R
import com.androdocs.weatherapp.controller.MainActivity
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


class WeekWeatherScreen : Fragment() {

    val city = Constants.city
    val api = Constants.api
    lateinit var cityView: TextView
    lateinit var firstImageView: ImageView
    lateinit var secondImageView: ImageView
    lateinit var thirdImageView: ImageView
    lateinit var fourthImageView: ImageView
    lateinit var fifthImageView: ImageView

    fun isNetworkAvailbale(): Boolean {
        val conManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val internetInfo = conManager.activeNetworkInfo
        return internetInfo != null && internetInfo.isConnected
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_week_weather_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cityView = view.findViewById(R.id.address)
        cityView.text = city

        firstImageView = view.findViewById<ImageView>(R.id.firstIconView)
        secondImageView = view.findViewById<ImageView>(R.id.secondIconView)
        thirdImageView = view.findViewById<ImageView>(R.id.thirdIconView)
        fourthImageView = view.findViewById<ImageView>(R.id.fourthIconView)
        fifthImageView = view.findViewById<ImageView>(R.id.fifthIconView)


        if (isNetworkAvailbale()) {
            WeatherTask().execute()
        } else {
            Toast.makeText(this.context, "Internet is not Connected", Toast.LENGTH_LONG).show()
        }

        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipeToRefresh)
        swipe.setOnRefreshListener {
            cityView.text = city
            if (isNetworkAvailbale()) {
                WeatherTask().execute()
            } else {
                Toast.makeText(this.context, "Internet is not Connected", Toast.LENGTH_LONG).show()
            }
            swipe.isRefreshing = false
        }
    }

    @SuppressLint("StaticFieldLeak")
    inner class WeatherTask : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()

            view!!.findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
            view!!.findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE
            view!!.findViewById<TextView>(R.id.errorText).visibility = View.GONE
        }

        override fun doInBackground(vararg params: String?): String? {
//onecall?lat=53.893009&lon=27.567444&exclude=hourly,current,minutely&units=metric&appid=29ce07b6457ed3fa2eb37330c490be99
            return try {
                URL("https://api.openweathermap.org/data/2.5/onecall?lat=${Constants.lat}&lon=${Constants.lon}" +
                        "&exclude=hourly,minutely&units=metric&appid=$api")
                    .readText(Charsets.UTF_8)
            } catch (e: Exception) {
                println(e.printStackTrace())
                return null
            }
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            try {
                val jsonObj = JSONObject(result)

                val jsonArray = JSONArray(result)
                for (i in 0 until 5) {
                    var item = jsonArray.getJSONObject(i)
                    val main = item.getJSONObject("main")
                    val sys = item.getJSONObject("sys")
                    val wind = item.getJSONObject("wind")
                    val weather = item.getJSONArray("weather").getJSONObject(0)

                    val updatedAt: Long = item.getLong("dt")
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
                    val icon = weather.getString("icon")

                    val address = item.getString("name") + ", " + sys.getString("country")


// there we are put our weather data in a view fragments


                    when (i) {
                        1 -> {
                            Picasso.with(context).load("http://openweathermap.org/img/wn/$icon@2x.png")
                                .into(firstImageView)
                        }
                        2 -> {
                            Picasso.with(context).load("http://openweathermap.org/img/wn/$icon@2x.png")
                                .into(secondImageView)
                        }
                        3 -> {
                            Picasso.with(context).load("http://openweathermap.org/img/wn/$icon@2x.png")
                                .into(thirdImageView)
                        }
                        4 -> {
                            Picasso.with(context).load("http://openweathermap.org/img/wn/$icon@2x.png")
                                .into(fourthImageView)
                        }
                        5 -> {
                            Picasso.with(context).load("http://openweathermap.org/img/wn/$icon@2x.png")
                                .into(fifthImageView)
                        }
                    }

                    view!!.findViewById<TextView>(R.id.address).text = address
                    view!!.findViewById<TextView>(R.id.updated_at).text = updatedAtText
                    view!!.findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
                    view!!.findViewById<TextView>(R.id.temp).text = temp
                    view!!.findViewById<TextView>(R.id.temp_min).text = tempMin
                    view!!.findViewById<TextView>(R.id.temp_max).text = tempMax
                    view!!.findViewById<TextView>(R.id.sunrise).text =
                        SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise * 1000))
                    view!!.findViewById<TextView>(R.id.sunset).text =
                        SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset * 1000))
                    view!!.findViewById<TextView>(R.id.wind).text = windSpeed
                    view!!.findViewById<TextView>(R.id.pressure).text = pressure
                    view!!.findViewById<TextView>(R.id.humidity).text = humidity

                    view!!.findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                    view!!.findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE
                }

            } catch (e: Exception) {
                view!!.findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                view!!.findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
            }
        }
    }
}