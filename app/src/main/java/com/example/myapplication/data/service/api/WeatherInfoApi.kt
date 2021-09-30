package com.example.myapplication.data.service.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherInfoApi(
    @SerialName("temp")
    val temperatureKelvin: Float,
    @SerialName("temp_min")
    val minTemperature: Float,
    @SerialName("temp_max")
    val maxTemperature: Float
)
