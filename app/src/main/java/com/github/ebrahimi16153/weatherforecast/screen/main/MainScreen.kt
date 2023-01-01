package com.github.ebrahimi16153.weatherforecast.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.github.ebrahimi16153.weatherforecast.R
import com.github.ebrahimi16153.weatherforecast.color.MyColors
import com.github.ebrahimi16153.weatherforecast.data.DataOrException
import com.github.ebrahimi16153.weatherforecast.model.Weather
import com.github.ebrahimi16153.weatherforecast.util.formatDate
import com.github.ebrahimi16153.weatherforecast.util.formatDaysDate
import com.github.ebrahimi16153.weatherforecast.util.weatherCondition
import com.github.ebrahimi16153.weatherforecast.viewmodel.MainViewModel
import kotlin.math.roundToInt

@ExperimentalMaterialApi
@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    city: String?
) {

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(isLoading = true)
    ) {
        value = mainViewModel.getWeather(city = city ?: "hashtgerd")
    }.value

    if (weatherData.isLoading == true) {

        Loading()
    } else if (weatherData.data != null) {

        MainScaffold(navController = navController, weather = weatherData.data)
    } else if (weatherData.exception?.message?.isNotEmpty() == true) {

        Error()
    }
}

@ExperimentalMaterialApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScaffold(
    navController: NavController,
    weather: Weather,
) {

    // bottom sheet
    BottomSheetScaffold(
        sheetBackgroundColor = MyColors().sheetBackground.value,
        sheetShape = CircleShape.copy(
            topStart = CornerSize(15.dp),
            topEnd = CornerSize(15.dp),
            bottomEnd = CornerSize(0.dp),
            bottomStart = CornerSize(0.dp)
        ),
        sheetContent = {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Surface(
                    modifier = Modifier
                        .padding(10.dp)
                        .height(5.dp)
                        .width(50.dp),
                    color = Color(0xf0634875),
                    shape = RoundedCornerShape(15.dp)
                ) {}

//                Spacer(modifier = Modifier.height(10.dp))
//
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 40.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Text(
//                        textAlign = TextAlign.Center,
//                        text = "Morning \n ${weather.list?.get(0)?.temp?.morn?.toInt()}°",
//                        color = MyColors().text.value,
//                        fontWeight = FontWeight.Bold
//                    )
//                    Text(
//                        textAlign = TextAlign.Center,
//                        text = "Day \n ${weather.list?.get(0)?.temp?.day?.toInt()}°",
//                        color = MyColors().text.value,
//                        fontWeight = FontWeight.Bold
//                    )
//                    Text(
//                        textAlign = TextAlign.Center,
//                        text = "night \n ${weather.list?.get(0)?.temp?.night?.toInt()}°",
//                        color = MyColors().text.value,
//                        fontWeight = FontWeight.Bold
//                    )
//                }


            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
            ) {
                //bottom of Bottom sheet

                Text(
                    text = "Bottom sheet",
                    fontSize = 60.sp
                )
            }
        },
        sheetPeekHeight = 50.dp
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {

            MainContent(weather = weather, navController = navController)
        }

    }

}
























