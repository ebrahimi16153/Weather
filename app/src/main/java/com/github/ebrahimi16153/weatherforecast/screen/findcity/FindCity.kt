package com.github.ebrahimi16153.weatherforecast.screen.findcity

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.ebrahimi16153.weatherforecast.color.MyColors
import com.github.ebrahimi16153.weatherforecast.model.CityDb
import com.github.ebrahimi16153.weatherforecast.navigation.WeatherScreenName
import com.github.ebrahimi16153.weatherforecast.viewmodel.CityViewModel
import com.github.ebrahimi16153.weatherforecast.widgets.CommonTextField

@Composable
fun FindCity(navController: NavController, cityViewModel: CityViewModel) {

    val cityQuery = remember{
        mutableStateOf("")
    }

    val validCity = remember(cityQuery.value) {
        cityQuery.value.trim().isNotEmpty()
    }

    Surface(modifier = Modifier.fillMaxSize(), color = MyColors().sheetBackground.value) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Enter your city ;)",
                color = MyColors().text.value,
                style = MaterialTheme.typography.caption
            )

            CommonTextField(valueState = cityQuery, placeHolder = "where do you live", onAction = KeyboardActions{
                if (!validCity) return@KeyboardActions
                cityViewModel.deleteAllCity()
                cityViewModel.insertCity(CityDb(city = cityQuery.value.trim()))
                cityQuery.value = ""
                navController.popBackStack()
                navController.navigate(WeatherScreenName.MainScreen.name)
            })

            // Button
            Button(
                onClick = {
                    if (!validCity) return@Button
                    cityViewModel.deleteAllCity()
                    cityViewModel.insertCity(CityDb(city = cityQuery.value.trim()))
                    cityQuery.value = ""
                    navController.popBackStack()
                    navController.navigate(WeatherScreenName.MainScreen.name)
                },
                shape = RoundedCornerShape(CornerSize(15.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = MyColors().sheetBackground.value),
                border = BorderStroke(width = 1.dp, color = MyColors().background.value)
            ) {
                Text(text = "Find")
            }
        }




    }
}
