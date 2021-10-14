package com.example.myapplication.data.mappers.apitoentity

import com.example.myapplication.data.service.api.WeatherTypeApi
import com.example.myapplication.domain.entity.WeatherTypeEntity
import javax.inject.Inject

class WeatherTypeApiToEntityMapper @Inject constructor() : (WeatherTypeApi) -> WeatherTypeEntity {

    override fun invoke(weatherType: WeatherTypeApi): WeatherTypeEntity {
        return WeatherTypeEntity(
            type = weatherType.type,
            description = weatherType.description
        )
    }
}
