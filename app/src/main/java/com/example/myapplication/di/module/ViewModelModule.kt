package com.example.myapplication.di.module

import androidx.lifecycle.ViewModel
import com.example.myapplication.di.ViewModelKey
import com.example.myapplication.presentation.findweather.FindWeatherViewModel
import com.example.myapplication.presentation.storageweather.StorageWeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @[IntoMap ViewModelKey(FindWeatherViewModel::class)]
    fun bindFindWeatherViewModel(findWeatherViewModel: FindWeatherViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(StorageWeatherViewModel::class)]
    fun bindStorageWeatherViewModel(storageWeatherViewModel: StorageWeatherViewModel): ViewModel
}
