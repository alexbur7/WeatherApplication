package com.example.myapplication.presentation.main

import android.app.Application
import android.content.Context
import com.example.myapplication.di.AppComponent
import com.example.myapplication.di.DaggerAppComponent
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(applicationContext)
            .build()
    }
}

@ExperimentalSerializationApi
val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }