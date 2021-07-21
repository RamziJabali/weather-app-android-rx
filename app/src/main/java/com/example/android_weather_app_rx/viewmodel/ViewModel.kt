package com.example.android_weather_app_rx.viewmodel

import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.example.android_weather_app_rx.network.WeatherForLocation
import com.example.android_weather_app_rx.model.WeatherRepository
import com.example.android_weather_app_rx.view.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ViewModel(private val weatherRepository: WeatherRepository): ViewModel() {
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var viewState = ViewState()

    private lateinit var view: MainActivity
    private val disposable = CompositeDisposable()

    fun startApplication() {
        disposable.add(weatherRepository.getWeatherForLocation(44418)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { weatherForLocation -> onSuccess(weatherForLocation) },
                { error -> onFailure(error.localizedMessage) }))

        viewState = viewState.copy(
            isLoadingDialog = true,
            viewOfText = View.GONE
        )

        invalidateView()
    }

    fun setMainActivity(mainActivity: MainActivity) {
        view = mainActivity
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    private fun onSuccess(weatherForLocation: WeatherForLocation) {
        viewState = viewState.copy(
            isLoadingDialog = false,
            didCallFail = false,
            viewOfText = View.VISIBLE,
            cityTitle = weatherForLocation.cityTitle,
            title = weatherForLocation.parentRegion.title,
            theTemp = getTemp(weatherForLocation),
            airPressure = weatherForLocation.consolidatedWeather[0].airPressure.toString(),
            humidity = weatherForLocation.consolidatedWeather[0].humidity.toString(),
            windSpeed = getWindSpeed(weatherForLocation)
        )
        invalidateView()
    }

    private fun onFailure(errorMessage: String?) {
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

    private fun getTemp(weatherForLocation: WeatherForLocation): String {
        val weatherInt = (weatherForLocation.consolidatedWeather[0].theTemp * 100).toInt()
        return ((weatherInt).toDouble() / 100).toString() + " F"
    }

    private fun getWindSpeed(weatherForLocation: WeatherForLocation): String {
        val weatherInt = (weatherForLocation.consolidatedWeather[0].windSpeed * 100).toInt()
        return ((weatherInt).toDouble() / 100).toString()
    }
}
