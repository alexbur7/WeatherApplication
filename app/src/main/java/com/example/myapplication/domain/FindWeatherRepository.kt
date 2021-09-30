package com.example.myapplication.domain

import com.example.myapplication.presentation.findweather.entity.WeatherEntity
import io.reactivex.rxjava3.core.Single

interface FindWeatherRepository {

    fun finWeatherByNameCity(city: String): Single<WeatherEntity>
}