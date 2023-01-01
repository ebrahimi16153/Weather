package com.github.ebrahimi16153.weatherforecast.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.github.ebrahimi16153.weatherforecast.R
import com.github.ebrahimi16153.weatherforecast.color.MyColors
import com.github.ebrahimi16153.weatherforecast.model.Weather
import com.github.ebrahimi16153.weatherforecast.util.formatDate
import com.github.ebrahimi16153.weatherforecast.util.formatDaysDate
import com.github.ebrahimi16153.weatherforecast.util.weatherCondition
import kotlin.math.roundToInt

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
fun MainContent(
    weather: Weather,
    navController: NavController,
    isDark: Boolean = isSystemInDarkTheme()
) {

    Column(modifier = Modifier.fillMaxSize()) {

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = if (isDark) {
                painterResource(id = R.drawable.night)
            } else {
                painterResource(id = R.drawable.day)
            },
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MyAppBar(navController = navController)
        LocationAndDate(weather = weather)
        WeatherIconAndDescription(weather = weather)
        Column(modifier = Modifier.fillMaxSize().padding(bottom = 70.dp), verticalArrangement = Arrangement.Bottom) {
            HumidityAndWindAndPressure(weather = weather)
        }

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


@Composable
fun HumidityAndWindAndPressure(weather: Weather) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        //humidity
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                modifier = Modifier.size(25.dp),
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "humidity",
                tint = MyColors().text.value
            )
            Text(
                text = weather.list?.get(0)?.humidity.toString() + " %",
                color = MyColors().text.value,
                style = MaterialTheme.typography.caption
            )
        }

        //pressure
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                modifier = Modifier.size(25.dp),
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "pressure",
                tint = MyColors().text.value
            )
            Text(
                text = weather.list?.get(0)?.pressure.toString() + " psi",
                color = MyColors().text.value,
                style = MaterialTheme.typography.caption
            )
        }
        //wind
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                modifier = Modifier.size(25.dp),
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "wind",
                tint = MyColors().text.value
            )
            Text(
                text = weather.list?.get(0)?.speed.toString() + " m/s",
                color = MyColors().text.value,
                style = MaterialTheme.typography.caption
            )
        }


    }

}