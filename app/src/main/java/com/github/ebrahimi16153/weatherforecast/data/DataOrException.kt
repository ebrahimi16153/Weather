package com.github.ebrahimi16153.weatherforecast.data

import kotlin.Exception

data class DataOrException<T, Boolean, Exception>(
    val data: T? = null,
    val isLoading: Boolean? = null,
    val exception: Exception? = null
)