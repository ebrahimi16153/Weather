package com.github.ebrahimi16153.weatherforecast.viewmodel

import androidx.lifecycle.ViewModel
import com.github.ebrahimi16153.weatherforecast.data.DataOrException
import com.github.ebrahimi16153.weatherforecast.model.Weather
import com.github.ebrahimi16153.weatherforecast.repository.MainRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: MainRepository):ViewModel() {

    suspend fun getWeather(city:String):DataOrException<Weather,Boolean,Exception>{
        return repository.getWeather(city = city)
    }

}