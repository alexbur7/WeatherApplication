package com.example.myapplication.data.mappers.entitytodb

import com.example.myapplication.data.db.entity.WeatherTypeDb
import com.example.myapplication.domain.entity.WeatherTypeEntity
import javax.inject.Inject

class WeatherTypeEntityToDbMapper @Inject constructor() : (WeatherTypeEntity) -> WeatherTypeDb {

    override fun invoke(weatherType: WeatherTypeEntity): WeatherTypeDb {
        return WeatherTypeDb(
            type = weatherType.type,
            description = weatherType.description
        )
    }
}
