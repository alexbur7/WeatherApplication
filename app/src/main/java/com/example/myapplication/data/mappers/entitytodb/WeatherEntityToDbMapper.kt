package com.example.myapplication.data.mappers.entitytodb

import com.example.myapplication.data.db.entity.WeatherDb
import com.example.myapplication.domain.entity.WeatherEntity
import javax.inject.Inject

class WeatherEntityToDbMapper @Inject constructor(
    private val weatherTypeMapper: WeatherTypeEntityToDbMapper,
    private val weatherInfoMapper: WeatherInfoEntityToDbMapper,
    private val windMapper: WindEntityToDbMapper
) : (WeatherEntity) -> WeatherDb {

    override fun invoke(weather: WeatherEntity): WeatherDb {
        return WeatherDb(
            nameCity = weather.nameCity,
            weatherTypes = weather.weather.map(weatherTypeMapper),
            weatherInfo = weatherInfoMapper(weather.weatherInfo),
            wind = windMapper(weather.wind)
        )
    }
}
