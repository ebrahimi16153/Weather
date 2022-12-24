package com.github.ebrahimi16153.weatherforecast.screen.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.github.ebrahimi16153.weatherforecast.R
import com.github.ebrahimi16153.weatherforecast.color.MyColors
import com.github.ebrahimi16153.weatherforecast.navigation.WeatherScreenName
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    Surface(modifier = Modifier.fillMaxSize(), color = MyColors().background.value) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
           // lottie splash animation
            val splashLottieFile by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.splash))
            //lottie loading animation
            val loading by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading))
            // lottie settings
            val progress by animateLottieCompositionAsState(
                composition = splashLottieFile,
                isPlaying = true,
                iterations = 5,
                speed = 1.0f
            )
            Spacer(modifier = Modifier.height(200.dp))
            //splash 
            LottieAnimation( modifier = Modifier.size(200.dp), composition = splashLottieFile, progress = { progress })
            Spacer(modifier = Modifier.height(200.dp))
            //loading
            LottieAnimation(alignment = Alignment.BottomCenter,modifier = Modifier.size(120.dp), composition = loading, progress = { progress })


            LaunchedEffect(key1 = true, block ={
                delay(3000)
                navController.popBackStack()
                navController.navigate(WeatherScreenName.MainScreen.name)
            } )
        }
    }



}