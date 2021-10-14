package com.example.myapplication.data.db.entity

import kotlinx.serialization.Serializable

@Serializable
data class WeatherTypeDb(
    val type: String,
    val description: String
)
