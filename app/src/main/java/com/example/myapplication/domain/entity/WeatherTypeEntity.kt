package com.example.myapplication.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherTypeEntity(
    val type: String,
    val description: String,
):Parcelable