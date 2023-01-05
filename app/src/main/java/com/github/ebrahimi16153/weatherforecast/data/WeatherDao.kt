package com.github.ebrahimi16153.weatherforecast.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.github.ebrahimi16153.weatherforecast.model.CityDb
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDao {

    @Query("SELECT * FROM city")
    fun getCityDb():Flow<List<CityDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(cityDb: CityDb)

    @Update
    suspend fun updateCity(cityDb: CityDb)

    @Query("DELETE FROM city")
    suspend fun deleteAllCity()

    @Delete
    suspend fun deleteCity(cityDb: CityDb)


}