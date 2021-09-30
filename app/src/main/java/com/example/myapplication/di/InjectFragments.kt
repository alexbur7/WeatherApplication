package com.example.myapplication.di

import com.example.myapplication.presentation.findweather.FindWeatherFragment
import com.example.myapplication.presentation.storageweather.StorageWeatherFragment

interface InjectFragments {

    fun inject(newWeatherFragment: FindWeatherFragment)

    fun inject(storageWeatherFragment: StorageWeatherFragment)
}