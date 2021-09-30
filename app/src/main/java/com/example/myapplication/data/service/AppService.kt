package com.example.myapplication.data.service

import com.example.myapplication.data.service.api.WeatherApi
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {

    @GET("data/2.5/weather?appid=7a6323e0b92a895c66628d5541cf0b4f")
    fun getWeather(
        @Query("q") city: String,
    ): Single<WeatherApi>
}