package com.example.android_weather_app_rx.koin

import com.example.android_weather_app_rx.model.UseCase
import com.example.android_weather_app_rx.model.WeatherRepository
import com.example.android_weather_app_rx.view.MainActivity
import com.example.android_weather_app_rx.network.JsonWeatherAPI
import com.example.android_weather_app_rx.viewmodel.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val weatherModule = module {

    viewModel { ViewModel(get()) }

    single<UseCase> { UseCase(get()) }

    single<WeatherRepository> { WeatherRepository(get<JsonWeatherAPI>()) }

}