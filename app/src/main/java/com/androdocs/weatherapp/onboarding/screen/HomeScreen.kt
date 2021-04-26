package com.androdocs.weatherapp.onboarding.screen

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.androdocs.weatherapp.R
import com.androdocs.weatherapp.service.ServiceWeather
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class HomeScreen : Fragment() {

    var theme = "dark"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        WeatherTask().execute()
        ServiceWeather(view).execute()
        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipeToRefresh)
        swipe.setOnRefreshListener {
//            WeatherTask().execute()
            ServiceWeather(view).execute()
            swipe.isRefreshing = false
        }

    }
}