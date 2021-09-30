package com.example.myapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.presentation.findweather.FindWeatherFragment
import com.example.myapplication.presentation.findweather.entity.WeatherEntity
import com.example.myapplication.presentation.storageweather.StorageWeatherFragment

class MainActivity : AppCompatActivity(), FindWeatherFragment.Callback, StorageWeatherFragment.Callback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, FindWeatherFragment.newInstance())
            .commit()
    }

    override fun openStorageWeatherFragment(weathers: List<WeatherEntity>) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, StorageWeatherFragment.newInstance(weathers))
            .addToBackStack(null)
            .commit()
    }

    override fun openFindWeatherFragment(weathers: List<WeatherEntity>) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, FindWeatherFragment.newInstance(weathers))
            .addToBackStack(null)
            .commit()
    }
}
