package com.github.ebrahimi16153.weatherforecast.util

import android.annotation.SuppressLint
import java.text.Format
import java.text.SimpleDateFormat




@SuppressLint("SimpleDateFormat")
fun formatDate(timestamp: Int): String {
    val sdf = SimpleDateFormat("E,MMM,d")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

@SuppressLint("SimpleDateFormat")
fun formatDays(timestamp: Int): String {
    val sdf = SimpleDateFormat("EEE")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

@SuppressLint("SimpleDateFormat")
fun formatDateTime(timestamp: Int): String {
    val sdf = SimpleDateFormat("hh:mm aa")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

fun formatDecimals(item: Double): String {
    return " %.0f".format(item)
}

fun formatDaysDate(value:String):String{
    val dayOfWeek = value.split(',')[0]
    val month = value.split(',')[1]
    val dayOfMonth = value.split(',')[2]

    val fullDay = when(dayOfWeek){

        "Sat" -> "Saturday"
        "Sun" -> "Sundays"
        "Mon" -> "Mondays"
        "Tue" -> "Tuesdays"
        "Wed" -> "Wednesdays"
        "Thu" -> "Thursdays"
        else -> "Fridays"
    }

    return "$fullDay, $month $dayOfMonth "

}