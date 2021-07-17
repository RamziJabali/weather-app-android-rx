package com.example.android_weather_app_rx.viewmodel

import android.view.View
import com.example.android_weather_app_rx.model.UseCase
import com.example.android_weather_app_rx.model.WeatherForLocation
import com.example.android_weather_app_rx.view.MainActivity

class ViewModel(private val view: MainActivity) {

    private var viewState = ViewState()
    private val useCase = UseCase(this)
    fun startApplication() {
        useCase.getWeatherForLocation(44418)
        viewState = viewState.copy(
            isLoadingDialog = true,
            viewOfText = View.GONE
        )
        invalidateView()
    }
    fun onSuccess(weatherForLocation: WeatherForLocation) {
        viewState = viewState.copy(
            isLoadingDialog = false,
            didCallFail = false,
            viewOfText = View.VISIBLE,
            cityTitle = weatherForLocation.cityTitle,
            title = weatherForLocation.parentRegion.title,
            theTemp = useCase.getTemp(weatherForLocation),
            airPressure = weatherForLocation.consolidatedWeather[0].airPressure.toString(),
            humidity = weatherForLocation.consolidatedWeather[0].humidity.toString(),
            windSpeed = useCase.getWindSpeed(weatherForLocation)
        )
        invalidateView()
    }
    fun onFailure(errorMessage: String?) {
        viewState = viewState.copy(
            isLoadingDialog = false,
            didCallFail = true,
            viewOfText = View.GONE,
            onFailureMessage = errorMessage!!
        )
        invalidateView()
    }
    private fun invalidateView() {
        view.setNewViewState(viewState)
    }
}
