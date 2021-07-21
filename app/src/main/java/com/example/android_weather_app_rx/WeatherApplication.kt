package com.example.android_weather_app_rx

import android.app.Application
import com.example.android_weather_app_rx.koin.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApplication : Application() {

    private val modules = listOf(
        networkModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            modules(modules)
        }
    }
}
