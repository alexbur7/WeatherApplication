package com.example.myapplication.di.module

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.db.WeatherDatabase
import com.example.myapplication.data.db.daos.WeatherDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun getWeatherDatabase(context: Context): WeatherDatabase {
        return Room.databaseBuilder(context, WeatherDatabase::class.java, "weatherDb")
            .build()
    }

    @Provides
    fun getWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao {
        return weatherDatabase.getWeatherDao()
    }
}
