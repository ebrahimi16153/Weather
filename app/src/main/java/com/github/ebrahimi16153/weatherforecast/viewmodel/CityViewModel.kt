package com.github.ebrahimi16153.weatherforecast.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ebrahimi16153.weatherforecast.model.CityDb
import com.github.ebrahimi16153.weatherforecast.repository.DbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CityViewModel @Inject constructor(private val dbRepository: DbRepository) : ViewModel() {

    private val _cityList = MutableStateFlow<List<CityDb>>(emptyList())

    val cityList = _cityList.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {

            dbRepository.getCity().distinctUntilChanged().collect() { listOfCity ->
                if (listOfCity.isEmpty()) {
                    Log.d("db", " db is empty")
                } else {
                    _cityList.value = listOfCity
                    Log.d("city", "${cityList.value}")
                }
            }


        }
    }


    fun insertCity(cityDb: CityDb) =
        viewModelScope.launch { dbRepository.insertCity(cityDb = cityDb) }
    fun updateCity(cityDb: CityDb) =
        viewModelScope.launch { dbRepository.deleteCity(cityDb = cityDb) }
    fun deleteAllCity() =
        viewModelScope.launch { dbRepository.deleteAllCity() }
    fun deleteCity(cityDb: CityDb) =
        viewModelScope.launch { dbRepository.deleteCity(cityDb = cityDb) }


}