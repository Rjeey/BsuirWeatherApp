package com.androdocs.weatherapp.onboarding.screen

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.androdocs.weatherapp.Constants
import com.androdocs.weatherapp.R
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

        firstImageView = view.findViewById(R.id.firstIconView)
        secondImageView = view.findViewById(R.id.secondIconView)
        thirdImageView = view.findViewById(R.id.thirdIconView)
        fourthImageView = view.findViewById(R.id.fourthIconView)
        fifthImageView = view.findViewById(R.id.fifthIconView)


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
                URL(
                    "https://api.openweathermap.org/data/2.5/onecall?lat=${Constants.lat}&lon=${Constants.lon}" +
                            "&exclude=hourly,minutely,alerts&units=metric&appid=$api"
                )
                    .readText(Charsets.UTF_8)
            } catch (e: Exception) {
                println(e.printStackTrace())
                return null
            }
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
//            println(result)
            try {
                val jsonObj = JSONObject(result)

                val address = jsonObj.get("timezone").toString().replace("/", ", ")

                val current = jsonObj.getJSONObject("current")
                val temp: Int = current.getInt("temp")


                val tempcon = "$temp °C"

                val updatedAt: Long = current.getLong("dt")
                val updatedAtText = "Updated at: " + SimpleDateFormat(
                    "dd/MM/yyyy hh:mm a",
                    Locale.ENGLISH
                ).format(Date(updatedAt * 1000))

                val weather = current.getJSONArray("weather").getJSONObject(0)
                val weatherDescription = weather.getString("description")

                view!!.findViewById<TextView>(R.id.address).text = address
                view!!.findViewById<TextView>(R.id.updated_at).text = updatedAtText
                view!!.findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
                view!!.findViewById<TextView>(R.id.temp).text = tempcon


                val jsonArray = jsonObj.getJSONArray("daily")
                println(jsonArray)
                setIcon(jsonArray)

                view!!.findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                view!!.findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE

            } catch (e: Exception) {
                e.printStackTrace()
                view!!.findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                view!!.findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
            }
        }
    }

    fun setIcon(jsonArray:JSONArray){
        for (i in 0..4) {
            var item = jsonArray.getJSONObject(i)
            var weather = item.getJSONArray("weather").getJSONObject(0)
            var icon = weather.get("icon").toString()

// there we are put our weather data in a view fragments
            when (i) {
                0 -> {
                    Picasso.with(context).load("https://openweathermap.org/img/wn/$icon@2x.png")
                        .into(firstImageView)
                    view?.findViewById<TextView>(R.id.bottom1)?.text = item.getJSONObject("temp").get("day").toString()
                }
                1 -> {
                    Picasso.with(context).load("https://openweathermap.org/img/wn/$icon@2x.png")
                        .into(secondImageView)
                    view?.findViewById<TextView>(R.id.bottom2)?.text = item.getJSONObject("temp").get("day").toString()
                }
                2 -> {
                    Picasso.with(context).load("https://openweathermap.org/img/wn/$icon@2x.png")
                        .into(thirdImageView)
                    view?.findViewById<TextView>(R.id.bottom3)?.text = item.getJSONObject("temp").get("day").toString()
                }
                3 -> {
                    Picasso.with(context).load("https://openweathermap.org/img/wn/$icon@2x.png")
                        .into(fourthImageView)
                    view?.findViewById<TextView>(R.id.bottom4)?.text = item.getJSONObject("temp").get("day").toString()
                }
                4 -> {
                    Picasso.with(context).load("https://openweathermap.org/img/wn/$icon@2x.png")
                        .into(fifthImageView)
                    view?.findViewById<TextView>(R.id.bottom5)?.text = item.getJSONObject("temp").get("day").toString()
                }
            }
        }
    }
}