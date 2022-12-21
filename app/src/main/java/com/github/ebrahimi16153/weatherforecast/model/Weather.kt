package com.github.ebrahimi16153.weatherforecast.model


import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("city")
    val city: City?,
    @SerializedName("cnt")
    val cnt: Int?, // 7
    @SerializedName("cod")
    val cod: String?, // 200
    @SerializedName("list")
    val list: List<WeatherItem>?,
    @SerializedName("message")
    val message: Double? // 9.2579462
)