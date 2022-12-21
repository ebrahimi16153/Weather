package com.github.ebrahimi16153.weatherforecast.model


import com.google.gson.annotations.SerializedName

data class Temp(
    @SerializedName("day")
    val day: Double?, // 278.47
    @SerializedName("eve")
    val eve: Double?, // 278.31
    @SerializedName("max")
    val max: Double?, // 278.58
    @SerializedName("min")
    val min: Double?, // 276.67
    @SerializedName("morn")
    val morn: Double?, // 277.35
    @SerializedName("night")
    val night: Double? // 278.11
)