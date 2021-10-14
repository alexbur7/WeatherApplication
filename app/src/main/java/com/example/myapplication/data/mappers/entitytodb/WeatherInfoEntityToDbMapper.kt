package com.example.myapplication.data.mappers.entitytodb

import com.example.myapplication.data.db.entity.WeatherInfoDb
import com.example.myapplication.domain.entity.WeatherInfoEntity
import javax.inject.Inject

class WeatherInfoEntityToDbMapper @Inject constructor() : (WeatherInfoEntity) -> WeatherInfoDb {

    override fun invoke(weatherInfo: WeatherInfoEntity): WeatherInfoDb {
        return WeatherInfoDb(
            temperature = weatherInfo.temperature,
            minTemperature = weatherInfo.minTemperature,
            maxTemperature = weatherInfo.maxTemperature
        )
    }
}
