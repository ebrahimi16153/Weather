package com.github.ebrahimi16153.weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.ebrahimi16153.weatherforecast.navigation.Navigation
import com.github.ebrahimi16153.weatherforecast.ui.theme.WeatherTheme
import dagger.hilt.android.AndroidEntryPoint
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            WeatherApp()

        }
    }
}
@ExperimentalMaterialApi
@Composable
fun WeatherApp(){
    WeatherTheme {
        Navigation()
    }
}