package com.example.myapplication.data.mapper

import com.example.myapplication.data.service.api.WeatherApi
import com.example.myapplication.presentation.findweather.entity.WeatherEntity
import javax.inject.Inject

class WeatherApiToEntityMapper @Inject constructor(
    private val weatherInfoApiToEntityMapper: WeatherInfoApiToEntityMapper,
    private val weatherTypeApiToEntityMapper: WeatherTypeApiToEntityMapper,
    private val windApiToEntityMapper: WindApiToEntityMapper
) : (WeatherApi, String) -> WeatherEntity {
    override fun invoke(weather: WeatherApi,nameCity: String): WeatherEntity {
        return WeatherEntity(
            nameCity = nameCity,
            weather = weather.weather.map(weatherTypeApiToEntityMapper),
            weatherInfo = weatherInfoApiToEntityMapper(weather.weatherInfo),
            wind = windApiToEntityMapper(weather.wind)
        )
    }
}
