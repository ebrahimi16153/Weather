package com.github.ebrahimi16153.weatherforecast.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class CityDb(
    @PrimaryKey
    @ColumnInfo(name = "city")
    val city:String
)