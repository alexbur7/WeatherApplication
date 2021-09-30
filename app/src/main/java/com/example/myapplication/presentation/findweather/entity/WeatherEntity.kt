package com.example.myapplication.presentation.findweather.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherEntity(
    val nameCity: String,
    val weather: List<WeatherTypeEntity>,
    val weatherInfo: WeatherInfoEntity,
    val wind: WindEntity
):Parcelable