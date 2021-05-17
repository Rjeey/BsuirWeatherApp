package com.androdocs.weatherapp.controller

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.androdocs.weatherapp.R
import java.util.*

class MainActivity :AppCompatActivity() {

    private var countryList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getCountries()
    }



    fun getCountries(){
        val start= Locale.getISOCountries();
        for (country:String in start) run {
            val locale = Locale("en", country)
            countryList.add(locale.displayCountry)
        }
    }

    fun openDialog(view: View){
        val bundle = AlertDialog.Builder(this)
        bundle.setTitle("Country List")

        bundle.setItems(countryList.toTypedArray()){ _, which ->
            Toast.makeText(applicationContext, countryList[which], Toast.LENGTH_LONG).show()
        }
        val dialog  = bundle.create()
        dialog.show()
    }
}