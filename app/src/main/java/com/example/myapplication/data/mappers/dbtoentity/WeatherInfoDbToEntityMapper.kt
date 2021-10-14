package com.example.myapplication.data.mappers.dbtoentity

import com.example.myapplication.data.db.entity.WeatherInfoDb
import com.example.myapplication.domain.entity.WeatherInfoEntity
import javax.inject.Inject

class WeatherInfoDbToEntityMapper @Inject constructor() : (WeatherInfoDb) -> WeatherInfoEntity {

    override fun invoke(weatherInfo: WeatherInfoDb): WeatherInfoEntity {
        return WeatherInfoEntity(
            temperature = weatherInfo.temperature,
            minTemperature = weatherInfo.minTemperature,
            maxTemperature = weatherInfo.maxTemperature
        )
    }
}
