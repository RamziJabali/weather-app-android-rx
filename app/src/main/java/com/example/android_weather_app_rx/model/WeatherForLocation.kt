package com.example.android_weather_app_rx.model

import com.example.android_weather_app_rx.model.ConsolidatedWeather
import com.example.android_weather_app_rx.model.Region
import com.google.gson.annotations.SerializedName

data class WeatherForLocation(
    @SerializedName("consolidated_weather")
    val consolidatedWeather: Array<ConsolidatedWeather>,

    @SerializedName("parent")
    val parentRegion: Region,

    @SerializedName("woeid")
    val whereOnEarthId: Int,

    @SerializedName("latt_long")
    val lat_long: String,

    @SerializedName("title")
    val cityTitle: String,

    @SerializedName("location_type")
    val locationType: String,

    @SerializedName("time")
    val dateTime: String,

    @SerializedName("timezone")
    val timezone: String,

    @SerializedName("sun_rise")
    val sunriseDateTime: String,

    @SerializedName("sun_set")
    val sunsetDateTime: String,

    @SerializedName("timezone_name")
    val timezoneName: String
)