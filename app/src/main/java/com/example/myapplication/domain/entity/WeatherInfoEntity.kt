package com.example.myapplication.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherInfoEntity(
    val temperature: Int,
    val minTemperature: Int,
    val maxTemperature: Int
):Parcelable
