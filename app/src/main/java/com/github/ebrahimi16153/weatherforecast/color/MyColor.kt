package com.github.ebrahimi16153.weatherforecast.color

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.github.ebrahimi16153.weatherforecast.ui.theme.darkBackground
import com.github.ebrahimi16153.weatherforecast.ui.theme.darkText
import com.github.ebrahimi16153.weatherforecast.ui.theme.lightBackground
import com.github.ebrahimi16153.weatherforecast.ui.theme.lightText


@SuppressLint("ComposableNaming")
@Composable
fun MyColors(dark: Boolean = isSystemInDarkTheme()): ColorModel {
    return if (dark) {
        ColorModel(
            background = remember {
                mutableStateOf(darkBackground)
            },
            text = remember {
                mutableStateOf(darkText)
            }
        )
    } else {
        ColorModel(
            background = remember {
                mutableStateOf(lightBackground)
            },
            text = remember {
                mutableStateOf(lightText)
            },


            )
    }
}