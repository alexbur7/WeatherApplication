package com.example.myapplication.data.service.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherApi(
    @SerialName("weather")
    val weather: List<WeatherTypeApi>,
    @SerialName("main")
    val weatherInfo: WeatherInfoApi,
    @SerialName("wind")
    val wind: WindApi
)
