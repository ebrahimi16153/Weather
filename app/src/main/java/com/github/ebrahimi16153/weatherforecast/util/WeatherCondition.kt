package com.github.ebrahimi16153.weatherforecast.util

import com.github.ebrahimi16153.weatherforecast.R

fun weatherCondition(icon: String): Int {

    val value = when (icon) {
        "01d" -> R.drawable.m01d
        "01n" -> R.drawable.m01n
        "02d" -> R.drawable.m02d
        "02n" -> R.drawable.m02n
        "03n" -> R.drawable.m03n
        "03d" -> R.drawable.m03d
        "04d" -> R.drawable.m04d
        "04n" -> R.drawable.m04n
        "09n" -> R.drawable.m09n
        "09d" -> R.drawable.m09d
        "10d" -> R.drawable.m10d
        "10n" -> R.drawable.m10n
        "11d" -> R.drawable.m11d
        "11n" -> R.drawable.m11n
        "13n" -> R.drawable.m13n
        "13d" -> R.drawable.m13d
        "50d" -> R.drawable.m50d
        else -> R.drawable.m50n

    }

    return value
}