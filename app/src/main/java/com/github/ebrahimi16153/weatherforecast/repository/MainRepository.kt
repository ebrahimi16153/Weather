package com.github.ebrahimi16153.weatherforecast.repository

import android.util.Log
import com.github.ebrahimi16153.weatherforecast.data.DataOrException
import com.github.ebrahimi16153.weatherforecast.model.Weather
import com.github.ebrahimi16153.weatherforecast.network.WeatherApi
import javax.inject.Inject

class MainRepository @Inject constructor(private val weatherApi: WeatherApi) {

    suspend fun getWeather(city: String): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            weatherApi.getWeather(query = city)
        } catch (e: Exception) {
            Log.e("error", "getWeather $e")
            return DataOrException(exception = e)
        }

        Log.d("Response", "getWeather $response")
        return DataOrException(data = response)
    }

}