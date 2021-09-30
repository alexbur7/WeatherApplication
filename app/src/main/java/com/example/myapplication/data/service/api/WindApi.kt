package com.example.myapplication.data.service.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WindApi(
    @SerialName("speed")
    val speed: Float
)
