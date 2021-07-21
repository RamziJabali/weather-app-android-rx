package com.example.android_weather_app_rx.network

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("title")
    val cityTitle: String,

    @SerializedName("location_type")
    val location_type: String,

    @SerializedName("woeid")
    val whereOnEarthId: Int,

    @SerializedName("latt_long")
    val lat_long: String
)