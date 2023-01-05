package com.github.ebrahimi16153.weatherforecast.repository

import com.github.ebrahimi16153.weatherforecast.data.WeatherDao
import com.github.ebrahimi16153.weatherforecast.model.CityDb
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DbRepository @Inject constructor(private val weatherDao: WeatherDao) {

    fun getCity(): Flow<List<CityDb>> = weatherDao.getCityDb()

    suspend fun insertCity(cityDb: CityDb) = weatherDao.insertCity(cityDb)
    suspend fun updateCity(cityDb: CityDb) = weatherDao.updateCity(cityDb)
    suspend fun deleteAllCity() = weatherDao.deleteAllCity()
    suspend fun deleteCity(cityDb: CityDb) = weatherDao.deleteCity(cityDb)



}