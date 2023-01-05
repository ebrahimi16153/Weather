package com.github.ebrahimi16153.weatherforecast.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.ebrahimi16153.weatherforecast.screen.main.MainScreen
import com.github.ebrahimi16153.weatherforecast.screen.splash.SplashScreen

@ExperimentalMaterialApi
@Composable
fun Navigation(){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreenName.SplashScreen.name){

        //splash screen
       composable(WeatherScreenName.SplashScreen.name){
           SplashScreen(navController = navController)
       }
        
        //Main screen
        composable(WeatherScreenName.MainScreen.name){
            MainScreen(navController = navController)
        }


    }
}