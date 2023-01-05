package com.github.ebrahimi16153.weatherforecast.data

import androidx.room.RoomDatabase
import androidx.room.Database
import com.github.ebrahimi16153.weatherforecast.model.CityDb

@Database(entities = [CityDb::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun dataBase(): WeatherDao

}