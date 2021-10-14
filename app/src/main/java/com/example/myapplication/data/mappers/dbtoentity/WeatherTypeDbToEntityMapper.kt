package com.example.myapplication.data.mappers.dbtoentity

import com.example.myapplication.data.db.entity.WeatherTypeDb
import com.example.myapplication.domain.entity.WeatherTypeEntity
import javax.inject.Inject

class WeatherTypeDbToEntityMapper @Inject constructor() : (WeatherTypeDb) -> WeatherTypeEntity {

    override fun invoke(weatherType: WeatherTypeDb): WeatherTypeEntity {
        return WeatherTypeEntity(
            type = weatherType.type,
            description = weatherType.description
        )
    }
}
