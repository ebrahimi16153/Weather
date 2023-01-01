package com.github.ebrahimi16153.weatherforecast.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.style.TextAlign
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

        MainScaffold(navController = navController, city = city, weather = weatherData.data)
    } else if (weatherData.exception?.message?.isNotEmpty() == true) {

        Error()
    }
}

@ExperimentalMaterialApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScaffold(navController: NavController, city: String?, weather: Weather) {

    // bottom sheet
    BottomSheetScaffold(
        sheetBackgroundColor = Color(0xf09E6598),
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

@Composable
fun Loading() {
    Surface(modifier = Modifier.fillMaxSize(), color = MyColors().background.value) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //loading animation
            val loading by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading))
            val progress by animateLottieCompositionAsState(
                composition = loading,
                isPlaying = true,
                iterations = LottieConstants.IterateForever,
                speed = 1.0f
            )
            LottieAnimation(
                composition = loading,
                progress = { progress },
                modifier = Modifier.size(180.dp)
            )
        }
    }
}


@Composable
fun Error() {
    Surface(modifier = Modifier.fillMaxSize(), color = MyColors().background.value) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //error animation
            val error by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.interneterror))
            val progress by animateLottieCompositionAsState(
                composition = error,
                isPlaying = true,
                iterations = LottieConstants.IterateForever,
                speed = 1.0f
            )
            LottieAnimation(
                composition = error,
                progress = { progress },
                modifier = Modifier.size(180.dp)
            )
            Text(
                text = "Sames your connection interrupted",
                style = MaterialTheme.typography.caption
            )
            Text(text = "check your Internet", style = MaterialTheme.typography.caption)

        }
    }

}

@ExperimentalMaterialApi
@Composable
fun MainContent(weather: Weather, navController: NavController) {

    Column(modifier = Modifier.fillMaxSize()) {

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.day),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MyAppBar(navController = navController)
        LocationAndDate(weather = weather)
        WeatherIconAndDescription(weather = weather)

    }

}

@Composable
fun MyAppBar(navController: NavController) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {},
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search",
                    tint = MyColors().text.value
                )
            }
        })
}


@Composable
fun LocationAndDate(weather: Weather) {
    //location
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Icon(
            modifier = Modifier
                .size(25.dp)
                .padding(top = 4.dp),
            imageVector = Icons.Rounded.LocationOn,
            contentDescription = "location icon",
            tint = MyColors().text.value
        )

        Text(
            modifier = Modifier.padding(start = 2.dp),
            text = "${weather.city?.name}, ${weather.city?.country}",
            color = MyColors().text.value,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

    }

    //date
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = formatDaysDate(formatDate(weather.list?.get(0)?.dt!!)),
            color = MyColors().text.value
        )
    }

}


@Composable
fun WeatherIconAndDescription(weather: Weather) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        // icon weather condition
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                modifier = Modifier.size(80.dp),
                painter =
                painterResource(
                    weatherCondition(weather.list?.get(0)?.weather?.get(0)?.icon.toString())
                ),
                contentDescription = "icon"
            )

            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "${weather.list?.get(0)?.weather?.get(0)?.description?.replaceFirstChar { it.uppercase() }}",
                color = MyColors().text.value,
                style = MaterialTheme.typography.caption
            )
        }

        // temp
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "${weather.list?.get(0)?.temp?.day?.toInt()}°",
                fontSize = 60.sp,
                color = MyColors().text.value,
            )

            Text(
                text = "Max: ${weather.list?.get(0)?.temp?.max?.roundToInt()}° | Min: ${
                    weather.list?.get(
                        0
                    )?.temp?.min?.roundToInt()
                }°",
                color = MyColors().text.value,
                style = MaterialTheme.typography.caption
            )
        }


    }

}






















