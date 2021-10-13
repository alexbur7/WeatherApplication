package com.example.myapplication.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.presentation.findweather.FindWeatherFragment
import com.example.myapplication.presentation.storageweather.StorageWeatherFragment

class MainActivity : AppCompatActivity(), Callback {

    private var isFirstScreen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            isFirstScreen = savedInstanceState.getBoolean(FIRST_SCREEN_KEY)
        }
        if (isFirstScreen) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FindWeatherFragment.newInstance())
                .commit()
            isFirstScreen = false
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(FIRST_SCREEN_KEY, isFirstScreen)
    }

    private companion object {
        const val FIRST_SCREEN_KEY = "first_screen_key"
    }

    override fun changeScreen() {
       // isFirstScreen = !isFirstScreen
    }
}

interface Callback {
    fun changeScreen()
}
