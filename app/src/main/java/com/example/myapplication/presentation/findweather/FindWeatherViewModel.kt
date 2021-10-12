package com.example.myapplication.presentation.findweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.FindWeatherRepository
import com.example.myapplication.presentation.SingleLiveEvent
import com.example.myapplication.presentation.utils.ErrorHandler
import com.example.myapplication.presentation.findweather.entity.WeatherEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FindWeatherViewModel @Inject constructor(
    private val findWeatherRepository: FindWeatherRepository
) : ViewModel() {

    val weatherLiveData: LiveData<WeatherEntity>
        get() = _weatherLiveData
    val errorLiveData: LiveData<Throwable>
        get() = _errorLiveData
    private val _weatherLiveData = MutableLiveData<WeatherEntity>()
    private val _errorLiveData = SingleLiveEvent<Throwable>()
    private val compositeDisposable = CompositeDisposable()
    private val weathers = arrayListOf<WeatherEntity>()
    val weathersLiveData = MutableLiveData(weathers)

    fun findWeather(city: String) {
        compositeDisposable.add(
            findWeatherRepository.finWeatherByNameCity(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _weatherLiveData.value = it
                    weathers.addWeather(it)
                }, {
                    _errorLiveData.value = it
                })
        )
    }

    private fun ArrayList<WeatherEntity>.addWeather(weatherEntity: WeatherEntity) {
        if (find { it.nameCity == weatherEntity.nameCity } == null) {
            add(weatherEntity)
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
