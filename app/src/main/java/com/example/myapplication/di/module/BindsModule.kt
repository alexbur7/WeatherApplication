package com.example.myapplication.di.module

import com.example.myapplication.data.repository.FindWeatherRepositoryImpl
import com.example.myapplication.domain.FindWeatherRepository
import dagger.Binds
import dagger.Module

@Module
interface BindsModule {

    @Binds
    fun bindsFindWeatherRepository(findWeatherRepositoryImpl: FindWeatherRepositoryImpl): FindWeatherRepository
}