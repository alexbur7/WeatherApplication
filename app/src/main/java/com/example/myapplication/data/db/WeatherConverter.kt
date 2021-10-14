package com.example.myapplication.data.db

import androidx.room.TypeConverter
import com.example.myapplication.data.db.entity.WeatherTypeDb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WeatherConverter {

    @TypeConverter
    fun weatherTypesToString(weatherTypes: List<WeatherTypeDb>): String {
        return Gson().toJson(weatherTypes)
    }

    @TypeConverter
    fun stringToWeatherTypes(weathersTypeString: String): List<WeatherTypeDb> {
        return Gson().fromJson(
            weathersTypeString,
            object : TypeToken<List<WeatherTypeDb>>() {}.type
        )
    }
}