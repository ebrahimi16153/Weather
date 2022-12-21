package com.github.ebrahimi16153.weatherforecast.color

import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color

data class ColorModel(
    val background: MutableState<Color>,
    val text:  MutableState<Color>,
)