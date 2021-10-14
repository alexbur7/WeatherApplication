package com.example.myapplication.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.presentation.findweather.FindWeatherFragment

class MainActivity : AppCompatActivity()  {

    private var isFirstActivityCreated = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            isFirstActivityCreated = savedInstanceState.getBoolean(FIRST_SCREEN_KEY)
        }
        if (isFirstActivityCreated) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FindWeatherFragment.newInstance())
                .commit()
            isFirstActivityCreated = false
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(FIRST_SCREEN_KEY, isFirstActivityCreated)
    }

    private companion object {
        const val FIRST_SCREEN_KEY = "first_screen_key"
    }
}
