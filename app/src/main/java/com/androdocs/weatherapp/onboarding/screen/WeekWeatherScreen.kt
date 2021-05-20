package com.androdocs.weatherapp.onboarding.screen

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.androdocs.weatherapp.Constants
import com.androdocs.weatherapp.LocationFinder
import com.androdocs.weatherapp.R
import com.androdocs.weatherapp.controller.MainActivity


class WeekWeatherScreen : Fragment() {

    val city = Constants.city
    val api = Constants.api
    val locationAddress= LocationFinder()

    fun  isNetworkAvailbale():Boolean{
        val conManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val internetInfo =conManager.activeNetworkInfo
        return internetInfo!=null && internetInfo.isConnected
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_week_weather_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationAddress.getAddressFromLocation(Constants.city, context,
            LocationFinder.Companion.GeoCoderHandler(context))

    }
}