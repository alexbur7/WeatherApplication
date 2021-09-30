package com.example.myapplication.presentation.findweather.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherTypeEntity(
    val type: String,
    val description: String,
):Parcelable