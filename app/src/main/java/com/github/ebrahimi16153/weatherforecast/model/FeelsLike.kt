package com.github.ebrahimi16153.weatherforecast.model


import com.google.gson.annotations.SerializedName

data class FeelsLike(
    @SerializedName("day")
    val day: Double?, // 278.47
    @SerializedName("eve")
    val eve: Double?, // 278.31
    @SerializedName("morn")
    val morn: Double?, // 276.09
    @SerializedName("night")
    val night: Double? // 278.11
)