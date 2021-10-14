package com.example.myapplication.data.db.entity

import androidx.room.ColumnInfo

data class WeatherInfoDb(
    val temperature: Int,
    @ColumnInfo(name = "temp_min")
    val minTemperature: Int,
    @ColumnInfo(name = "temp_max")
    val maxTemperature: Int
)
