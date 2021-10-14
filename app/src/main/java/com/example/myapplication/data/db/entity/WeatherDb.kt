package com.example.myapplication.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Weather")
data class WeatherDb(
    @PrimaryKey
    val nameCity: String,
    @ColumnInfo(name = "weather_types")
    val weatherTypes: List<WeatherTypeDb>,
    @Embedded
    val weatherInfo: WeatherInfoDb,
    @Embedded
    val wind: WindDb
)
