package com.example.myapplication.data.service.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherTypeApi(
    @SerialName("main")
    val type: String,
    @SerialName("description")
    val description: String
)