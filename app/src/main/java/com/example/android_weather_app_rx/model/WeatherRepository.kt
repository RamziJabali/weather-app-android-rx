package com.example.android_weather_app_rx.model

import com.example.android_weather_app_rx.viewmodel.JsonWeatherAPI
import com.example.android_weather_app_rx.viewmodel.RetrofitBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherRepository(val useCase: UseCase) {

    fun getWeatherForLocation(WOEID: Int){
        val retrofit = RetrofitBuilder().getInstance()
        val jsonWeatherApi = retrofit.create(JsonWeatherAPI::class.java)
        jsonWeatherApi.getWeatherForWhereOnEarthId(WOEID)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { weatherForLocation -> useCase.onSuccess(weatherForLocation) },                  // onSuccess
                { error -> useCase.onFailure(error.localizedMessage) })
    }
}