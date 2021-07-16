package com.example.android_weather_app_rx.viewmodel

import com.example.android_weather_app_rx.model.WeatherForLocation
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonWeatherAPI {

    @GET("/api/location/{whereOnEarthId}")
    fun getWeatherForWhereOnEarthId(@Path("whereOnEarthId") whereOnEarthId: Int): Flowable<WeatherForLocation>
}