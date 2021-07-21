package com.example.android_weather_app_rx.koin


import com.example.android_weather_app_rx.network.JsonWeatherAPI
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://www.metaweather.com"

val networkModule = module {

    single<JsonWeatherAPI> {
        get<Retrofit>().create(JsonWeatherAPI::class.java)
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .build()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder().build()
    }

    single<GsonConverterFactory> {
        GsonConverterFactory.create()
    }

    single<Gson> {
        Gson()
    }

    single<RxJava2CallAdapterFactory> {
        RxJava2CallAdapterFactory.create()
    }
}