package com.example.myapplication.domain.repostitory

import com.example.myapplication.domain.entity.WeatherEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface StorageWeatherRepository {

    fun insertWeatherInDb(weatherEntity: WeatherEntity)

    fun deleteWeathersFromDb(): Completable

    fun getWeathersFromDb(): Single<List<WeatherEntity>>

    fun getLastWeather(): Maybe<WeatherEntity>

    fun deleteWeather(nameCity: String): Completable
}