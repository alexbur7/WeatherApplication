package com.example.myapplication.domain.repostitory

import com.example.myapplication.domain.entity.WeatherEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface StorageWeatherRepository {

    fun insertWeatherInDb(weatherEntity: WeatherEntity): Completable

    fun deleteWeathersFromDb(): Completable

    fun getWeathersFromDb(): Single<List<WeatherEntity>>
}