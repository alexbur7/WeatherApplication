package com.example.myapplication.domain.repostitory

import com.example.myapplication.domain.entity.WeatherEntity
import io.reactivex.rxjava3.core.Single

interface FindWeatherRepository {

    fun finWeatherByNameCity(city: String): Single<WeatherEntity>
}