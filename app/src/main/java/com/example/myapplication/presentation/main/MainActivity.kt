package com.example.myapplication.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.presentation.findweather.FindWeatherFragment
import com.example.myapplication.presentation.storageweather.StorageWeatherFragment

class MainActivity : AppCompatActivity() {

    private var isFirstActivityCreated = true
    private val viewBinding by viewBinding(ActivityMainBinding::bind)

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

        with(viewBinding) {
            bottomNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.find -> {
                        if (supportFragmentManager.fragments.find { it is FindWeatherFragment } == null) {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, FindWeatherFragment.newInstance())
                                .commit()
                        }
                    }
                    R.id.history -> {
                        if (supportFragmentManager.fragments.find { it is StorageWeatherFragment } == null) {
                            supportFragmentManager.beginTransaction()
                                .replace(
                                    R.id.fragment_container,
                                    StorageWeatherFragment.newInstance()
                                )
                                .commit()
                        }
                    }
                }
                return@setOnItemSelectedListener true
            }
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
