package com.example.android_weather_app_rx.model

import com.example.android_weather_app_rx.network.WeatherForLocation
import com.example.android_weather_app_rx.viewmodel.ViewModel
import io.reactivex.Observable

class UseCase(private val repo: WeatherRepository) {

    fun getWeatherForLocation(woeid: Int): Observable<Weather> =
        repo.getWeatherForLocation(woeid)
            .map { weatherLocation ->
                Weather(
                    title =  weatherLocation.parentRegion.title,

                    cityTitle =  weatherLocation.cityTitle,

                    windSpeed = getWindSpeed(weatherLocation),

                    humidity = weatherLocation.consolidatedWeather[0].humidity.toString(),

                    airPressure = weatherLocation.consolidatedWeather[0].airPressure.toString(),

                    theTemp = getTemp(weatherLocation)
                )
            }

    fun getTemp(weatherForLocation: WeatherForLocation): String {
        val weatherInt = (weatherForLocation.consolidatedWeather[0].theTemp * 100).toInt()
        return ((weatherInt).toDouble() / 100).toString() + " F"
    }

    fun getWindSpeed(weatherForLocation: WeatherForLocation): String {
        val weatherInt = (weatherForLocation.consolidatedWeather[0].windSpeed * 100).toInt()
        return ((weatherInt).toDouble() / 100).toString()
    }

}