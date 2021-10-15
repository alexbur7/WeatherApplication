package com.example.myapplication.presentation.storageweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.entity.WeatherEntity
import com.example.myapplication.domain.repostitory.StorageWeatherRepository
import com.example.myapplication.presentation.SingleLiveEvent
import com.example.myapplication.presentation.utils.ErrorHandler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class StorageWeatherViewModel @Inject constructor(
    private val storageWeatherRepository: StorageWeatherRepository,
    private val errorHandler: ErrorHandler
) : ViewModel() {

    val weathersLiveData: LiveData<List<WeatherEntity>>
        get() = _weathersLiveData
    val errorTextIdLiveData: LiveData<Int>
        get() = _errorTextIdLiveData
    private val _weathersLiveData = MutableLiveData<List<WeatherEntity>>()
    private val _errorTextIdLiveData = SingleLiveEvent<Int>()

    init {
        getWeathers()
    }

    fun deleteWeathers() {
        storageWeatherRepository.deleteWeathersFromDb()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _weathersLiveData.value = emptyList()
            }, {
                _errorTextIdLiveData.value = errorHandler.getErrorStringIdByThrowable(it)
            })
    }

    fun deleteWeather(nameCity: String){
        storageWeatherRepository.deleteWeather(nameCity)
            .andThen(
                storageWeatherRepository.getWeathersFromDb()
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _weathersLiveData.value = it
            }, {
                _errorTextIdLiveData.value = errorHandler.getErrorStringIdByThrowable(it)
            })
    }

    private fun getWeathers() {
        storageWeatherRepository.getWeathersFromDb()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _weathersLiveData.value = it
            }, {
                _errorTextIdLiveData.value = errorHandler.getErrorStringIdByThrowable(it)
            })
    }

}