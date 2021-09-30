package com.example.myapplication.data.repository

import com.example.myapplication.data.mapper.WeatherApiToEntityMapper
import com.example.myapplication.data.service.AppService
import com.example.myapplication.domain.FindWeatherRepository
import com.example.myapplication.presentation.findweather.entity.WeatherEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FindWeatherRepositoryImpl @Inject constructor(
    private val weatherMapper: WeatherApiToEntityMapper,
    private val appService: AppService
) : FindWeatherRepository {

    override fun finWeatherByNameCity(city: String): Single<WeatherEntity> {
        return appService.getWeather(city)
            .map {
                weatherMapper.invoke(it, city)
            }
    }
}
