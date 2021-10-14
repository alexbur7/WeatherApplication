package com.example.myapplication.data.repository

import com.example.myapplication.data.db.daos.WeatherDao
import com.example.myapplication.data.mappers.dbtoentity.WeatherDbToEntityMapper
import com.example.myapplication.data.mappers.entitytodb.WeatherEntityToDbMapper
import com.example.myapplication.domain.entity.WeatherEntity
import com.example.myapplication.domain.repostitory.StorageWeatherRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class StorageWeatherRepositoryImpl @Inject constructor(
    private val weatherDao: WeatherDao,
    private val weatherMapper: WeatherEntityToDbMapper,
    private val weatherDbToEntityMapper: WeatherDbToEntityMapper
) : StorageWeatherRepository {

    override fun insertWeatherInDb(weatherEntity: WeatherEntity) {
        return weatherDao.insertWeather(weatherMapper(weatherEntity))
    }

    override fun deleteWeathersFromDb(): Completable {
        return weatherDao.deleteWeathers()
    }

    override fun getWeathersFromDb(): Single<List<WeatherEntity>> {
        return weatherDao.getWeathers()
            .map { weathers ->
                weathers.map(weatherDbToEntityMapper)
            }
            .toSingle()
    }
}
