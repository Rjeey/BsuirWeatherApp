package com.androdocs.weatherapp.onboarding.screen

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.androdocs.weatherapp.R


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
        }, 6000)
        return inflater.inflate(R.layout.splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val topAnim = AnimationUtils.loadAnimation(this.context, R.anim.top_animation)
        val middleAnim = AnimationUtils.loadAnimation(this.context, R.anim.middle_animation)
        val bottomAnim = AnimationUtils.loadAnimation(this.context, R.anim.bottom_animation)


        view.findViewById<ImageView>(R.id.WeatherImage).startAnimation(topAnim)
        view.findViewById<TextView>(R.id.TextView).startAnimation(middleAnim)
        view.findViewById<TextView>(R.id.TextView2).startAnimation(middleAnim)
        view.findViewById<TextView>(R.id.author).startAnimation(bottomAnim)
    }
}