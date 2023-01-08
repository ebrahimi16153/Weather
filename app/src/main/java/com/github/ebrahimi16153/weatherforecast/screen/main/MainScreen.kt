package com.github.ebrahimi16153.weatherforecast.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.ebrahimi16153.weatherforecast.color.MyColors
import com.github.ebrahimi16153.weatherforecast.data.DataOrException
import com.github.ebrahimi16153.weatherforecast.model.CityDb
import com.github.ebrahimi16153.weatherforecast.model.Weather
import com.github.ebrahimi16153.weatherforecast.navigation.WeatherScreenName
import com.github.ebrahimi16153.weatherforecast.screen.findcity.FindCity
import com.github.ebrahimi16153.weatherforecast.viewmodel.CityViewModel
import com.github.ebrahimi16153.weatherforecast.viewmodel.MainViewModel
import com.github.ebrahimi16153.weatherforecast.widgets.CommonTextField

@ExperimentalMaterialApi
@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    cityViewModel: CityViewModel = hiltViewModel()
) {

    val cityFromDb = cityViewModel.cityList.collectAsState().value

    if (cityFromDb.isNotEmpty()) {
        val city = cityFromDb[0].city
        val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
            initialValue = DataOrException(isLoading = true)
        ) {
            value = mainViewModel.getWeather(city = city)
        }.value

        if (weatherData.isLoading == true) {

            Loading()
        } else if (weatherData.data != null) {

            MainScaffold(navController = navController, weather = weatherData.data)
        } else if (weatherData.exception?.message?.isNotEmpty() == true) {
                if (weatherData.exception.message.toString() == "HTTP 404 Not Found"){
                    FindCity(navController = navController, cityViewModel =cityViewModel )
                }else{
            Error()
                }

        }
    } else {

        FindCity(navController = navController,cityViewModel = cityViewModel)
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

           ListOfWeek(navController = navController, weather = weather)
            }
        },
        sheetPeekHeight = 50.dp
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {

            MainContent(weather = weather, navController = navController)
        }

    }

}
























