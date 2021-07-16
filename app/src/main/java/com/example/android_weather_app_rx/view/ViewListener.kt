package com.example.android_weather_app_rx.view

import com.example.android_weather_app_rx.viewmodel.ViewState

interface ViewListener {
    fun setNewViewState(viewState: ViewState)
}