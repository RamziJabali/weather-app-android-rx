package com.example.android_weather_app_rx.model

import android.annotation.SuppressLint
import com.example.android_weather_app_rx.viewmodel.JsonWeatherAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinApplication
import org.koin.core.KoinComponent
import org.koin.core.get
import retrofit2.Retrofit

class WeatherRepository(private val useCase: UseCase):KoinComponent {

    @SuppressLint("CheckResult")
    fun getWeatherForLocation(WOEID: Int){

        get<JsonWeatherAPI>().getWeatherForWhereOnEarthId(WOEID)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { weatherForLocation -> useCase.onSuccess(weatherForLocation) }, // onSuccess
                { error -> useCase.onFailure(error.localizedMessage) }) //onError
    }
}