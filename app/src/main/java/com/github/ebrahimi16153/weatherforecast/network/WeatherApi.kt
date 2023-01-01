package com.github.ebrahimi16153.weatherforecast.network

import com.github.ebrahimi16153.weatherforecast.model.Weather
import com.github.ebrahimi16153.weatherforecast.util.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Singleton
interface WeatherApi {

    @GET("data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query(value = "q") query: String,
        @Query(value = "units") unit: String = "metric",
        @Query(value = "appid") appid:String = API_KEY
    ):Weather
}