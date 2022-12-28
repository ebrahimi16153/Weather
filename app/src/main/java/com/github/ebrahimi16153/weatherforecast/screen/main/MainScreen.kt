package com.github.ebrahimi16153.weatherforecast.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.github.ebrahimi16153.weatherforecast.R
import com.github.ebrahimi16153.weatherforecast.color.MyColors
import com.github.ebrahimi16153.weatherforecast.data.DataOrException
import com.github.ebrahimi16153.weatherforecast.model.Weather
import com.github.ebrahimi16153.weatherforecast.viewmodel.MainViewModel

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    city: String?
) {

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(isLoading = true)
    ) {
        value = mainViewModel.getWeather(city = city ?: "london")
    }.value

    if (weatherData.isLoading == true) {

        Loading()
    } else if (weatherData.data != null) {

        MainScaffold(navController = navController, city = city, weather = weatherData.data)
    } else if (weatherData.exception?.message?.isNotEmpty() == true) {

        Error()
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScaffold(navController: NavController, city: String?, weather: Weather) {

    Scaffold {

        Surface(modifier = Modifier.fillMaxSize(), color = MyColors().background.value) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                MainContent(weather = weather, navController = navController)

            }
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
fun MainContent(weather: Weather, navController: NavController) {
    Surface(modifier = Modifier.fillMaxWidth(), color = MyColors().background.value) {

        Box(modifier = Modifier.fillMaxSize()) {
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

        }





    }


}



