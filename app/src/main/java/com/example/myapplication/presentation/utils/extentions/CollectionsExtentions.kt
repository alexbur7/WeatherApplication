package com.example.myapplication.presentation.utils.extentions

import com.example.myapplication.domain.entity.WeatherEntity

fun ArrayList<WeatherEntity>.addWeather(weatherEntity: WeatherEntity) {
    if (find { it.nameCity == weatherEntity.nameCity } == null) {
        add(weatherEntity)
    }
}