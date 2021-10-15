package com.example.myapplication.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.myapplication.data.db.entity.WeatherDb
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

@Dao
interface WeatherDao {

    @Insert(onConflict = REPLACE)
    fun insertWeather(weatherDb: WeatherDb)

    @Query("DELETE FROM Weather")
    fun deleteWeathers(): Completable

    @Query("SELECT * FROM Weather")
    fun getWeathers(): Maybe<List<WeatherDb>>

    @Query("DELETE FROM Weather WHERE nameCity=:nameCity")
    fun deleteWeather(nameCity: String): Completable
}
