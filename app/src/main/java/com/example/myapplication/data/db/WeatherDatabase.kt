package com.example.myapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.db.daos.WeatherDao
import com.example.myapplication.data.db.entity.WeatherDb

@Database(
    entities = [
        WeatherDb::class
    ],
    version = 1, exportSchema = false
)
@TypeConverters(value = [WeatherConverter::class])
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun getWeatherDao(): WeatherDao
}
