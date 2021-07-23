package com.example.android_weather_app_rx.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.android_weather_app_rx.view.MainActivity

data class ViewState(

    val isLoadingDialog: Boolean = false,

    val didCallFail: Boolean = false,

    val title: String = "",

    val cityTitle: String = "",

    val windSpeed: String = "",

    val humidity: String = "",

    val airPressure: String = "",

    val theTemp: String = "",

    val onFailureMessage: String = "",

    val failedResponseMessage: String = "",

    val viewOfText: Int = View.GONE
)
