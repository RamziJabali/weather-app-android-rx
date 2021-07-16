package com.example.android_weather_app_rx.viewmodel

import com.example.android_weather_app_rx.view.MainActivity
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    fun getInstance() = Retrofit.Builder()
        .baseUrl(MainActivity.API_BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}