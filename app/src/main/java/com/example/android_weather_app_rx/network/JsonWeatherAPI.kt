package com.example.android_weather_app_rx.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonWeatherAPI {

    @GET("/api/location/{whereOnEarthId}")
    fun getWeatherForWhereOnEarthId(@Path("whereOnEarthId") whereOnEarthId: Int): Observable<WeatherForLocation>
}