package com.example.myapplication.data.mapper

import com.example.myapplication.data.service.api.WeatherInfoApi
import com.example.myapplication.presentation.findweather.entity.WeatherInfoEntity
import javax.inject.Inject
import kotlin.math.roundToInt

class WeatherInfoApiToEntityMapper @Inject constructor() : (WeatherInfoApi) -> WeatherInfoEntity {
    override fun invoke(weatherInfo: WeatherInfoApi): WeatherInfoEntity {
        return WeatherInfoEntity(
            temperature = (weatherInfo.temperatureKelvin - 273).roundToInt(),
            minTemperature = (weatherInfo.minTemperature - 273).roundToInt(),
            maxTemperature = (weatherInfo.maxTemperature - 273).roundToInt()
        )
    }
}
