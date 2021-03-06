package com.example.myapplication.data.repository

import com.example.myapplication.data.mappers.apitoentity.WeatherApiToEntityMapper
import com.example.myapplication.data.service.AppService
import com.example.myapplication.domain.repostitory.FindWeatherRepository
import com.example.myapplication.domain.entity.WeatherEntity
import com.example.myapplication.domain.repostitory.StorageWeatherRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FindWeatherRepositoryImpl @Inject constructor(
    private val weatherMapper: WeatherApiToEntityMapper,
    private val appService: AppService,
    private val storageWeatherRepository: StorageWeatherRepository
) : FindWeatherRepository {

    override fun finWeatherByNameCity(city: String): Single<WeatherEntity> {
        return appService.getWeather(city)
            .map {
                weatherMapper.invoke(it, city)
            }
            .doOnSuccess {
                storageWeatherRepository.insertWeatherInDb(it)
            }
    }
}
