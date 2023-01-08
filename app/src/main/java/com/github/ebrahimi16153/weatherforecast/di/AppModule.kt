package com.github.ebrahimi16153.weatherforecast.di

import android.content.Context
import androidx.room.Room
import com.github.ebrahimi16153.weatherforecast.data.Database
import com.github.ebrahimi16153.weatherforecast.data.WeatherDao
import com.github.ebrahimi16153.weatherforecast.network.WeatherApi
import com.github.ebrahimi16153.weatherforecast.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    //di for Dao
    @Singleton
    @Provides
    fun providesWeatherDao(database: Database): WeatherDao = database.dataBase()

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, "database")
            .fallbackToDestructiveMigration().build()


    // di for retrofit api
    @Provides
    @Singleton
    fun provideOpenWeatherApi(): WeatherApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(WeatherApi::class.java)
    }
}