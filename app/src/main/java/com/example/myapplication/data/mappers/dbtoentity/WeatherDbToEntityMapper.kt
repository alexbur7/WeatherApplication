package com.example.myapplication.data.mappers.dbtoentity

import com.example.myapplication.data.db.entity.WeatherDb
import com.example.myapplication.domain.entity.WeatherEntity
import javax.inject.Inject

class WeatherDbToEntityMapper @Inject constructor(
    private val weatherTypeMapper: WeatherTypeDbToEntityMapper,
    private val weatherInfoMapper: WeatherInfoDbToEntityMapper,
    private val windMapper: WindDbToEntityMapper
) : (WeatherDb) -> WeatherEntity {

    override fun invoke(weather: WeatherDb): WeatherEntity {
        return WeatherEntity(
            nameCity = weather.nameCity,
            weather = weather.weatherTypes.map(weatherTypeMapper),
            weatherInfo = weatherInfoMapper(weather.weatherInfo),
            wind = windMapper(weather.wind)
        )
    }
}
