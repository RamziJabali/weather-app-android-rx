package com.example.android_weather_app_rx.model

import android.annotation.SuppressLint
import com.example.android_weather_app_rx.network.JsonWeatherAPI
import com.example.android_weather_app_rx.network.WeatherForLocation
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherRepository(private val jsonWeatherApi: JsonWeatherAPI) {

    fun getWeatherForLocation(woeid: Int): Observable<WeatherForLocation> =
        jsonWeatherApi.getWeatherForWhereOnEarthId(woeid)
}