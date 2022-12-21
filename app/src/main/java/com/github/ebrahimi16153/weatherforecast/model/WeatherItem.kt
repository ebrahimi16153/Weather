package com.github.ebrahimi16153.weatherforecast.model


import com.google.gson.annotations.SerializedName

data class WeatherItem (
    @SerializedName("clouds")
    val clouds: Int?, // 100
    @SerializedName("deg")
    val deg: Int?, // 185
    @SerializedName("dt")
    val dt: Int?, // 1671620400
    @SerializedName("feels_like")
    val feelsLike: FeelsLike?,
    @SerializedName("gust")
    val gust: Double?, // 3.48
    @SerializedName("humidity")
    val humidity: Int?, // 92
    @SerializedName("pop")
    val pop: Double?, // 0.09
    @SerializedName("pressure")
    val pressure: Int?, // 1020
    @SerializedName("rain")
    val rain: Double?, // 0.62
    @SerializedName("speed")
    val speed: Double?, // 1.71
    @SerializedName("sunrise")
    val sunrise: Int?, // 1671605323
    @SerializedName("sunset")
    val sunset: Int?, // 1671637158
    @SerializedName("temp")
    val temp: Temp?,
    @SerializedName("weather")
    val weather: List<WeatherObject>?
)