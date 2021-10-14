package com.example.myapplication.di.module

import com.example.myapplication.data.repository.FindWeatherRepositoryImpl
import com.example.myapplication.data.repository.StorageWeatherRepositoryImpl
import com.example.myapplication.domain.repostitory.FindWeatherRepository
import com.example.myapplication.domain.repostitory.StorageWeatherRepository
import dagger.Binds
import dagger.Module

@Module
interface BindsModule {

    @Binds
    fun bindsFindWeatherRepository(findWeatherRepositoryImpl: FindWeatherRepositoryImpl)
            : FindWeatherRepository

    @Binds
    fun bindStorageWeatherRepository(storageWeatherRepositoryImpl: StorageWeatherRepositoryImpl)
            : StorageWeatherRepository
}
