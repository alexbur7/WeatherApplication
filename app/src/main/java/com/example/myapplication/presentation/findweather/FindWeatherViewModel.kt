package com.example.myapplication.presentation.findweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.FindWeatherRepository
import com.example.myapplication.presentation.SingleLiveEvent
import com.example.myapplication.presentation.findweather.entity.WeatherEntity
import com.example.myapplication.presentation.utils.ErrorHandler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FindWeatherViewModel @Inject constructor(
    private val findWeatherRepository: FindWeatherRepository,
    private val errorHandler: ErrorHandler
) : ViewModel() {

    val weatherLiveData: LiveData<WeatherEntity>
        get() = _weatherLiveData
    val errorTextIdLiveData: LiveData<Int>
        get() = _errorTextIdLiveData
    private val _weatherLiveData = MutableLiveData<WeatherEntity>()
    private val _errorTextIdLiveData = SingleLiveEvent<Int>()
    private val compositeDisposable = CompositeDisposable()

    fun findWeather(city: String) {
        compositeDisposable.add(
            findWeatherRepository.finWeatherByNameCity(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _weatherLiveData.value = it
                }, {
                    _errorTextIdLiveData.value = errorHandler.getErrorStringIdByThrowable(it)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
