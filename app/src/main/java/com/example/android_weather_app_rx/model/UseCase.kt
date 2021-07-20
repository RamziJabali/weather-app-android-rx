package com.example.android_weather_app_rx.model

import com.example.android_weather_app_rx.viewmodel.JsonWeatherAPI
import com.example.android_weather_app_rx.viewmodel.ViewModel
import org.koin.core.context.GlobalContext.get

class UseCase(val viewModel: ViewModel) {
    fun getWeatherForLocation(WOEID: Int){
        WeatherRepository(this).getWeatherForLocation(WOEID)
    }

    fun onSuccess(weatherForLocation: WeatherForLocation) {
        viewModel.onSuccess(weatherForLocation)
    }

    fun onFailure(errorMessage: String?){
        viewModel.onFailure(errorMessage)
    }

    fun getTemp(weatherForLocation: WeatherForLocation):String {
        val weatherInt= (weatherForLocation.consolidatedWeather[0].theTemp * 100).toInt()
        return ((weatherInt).toDouble() / 100).toString() + " F"
    }

    fun getWindSpeed(weatherForLocation: WeatherForLocation):String{
        val weatherInt= (weatherForLocation.consolidatedWeather[0].windSpeed * 100).toInt()
        return ((weatherInt).toDouble() / 100).toString()
    }

}