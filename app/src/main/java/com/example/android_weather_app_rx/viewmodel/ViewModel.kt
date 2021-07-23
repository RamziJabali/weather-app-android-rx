package com.example.android_weather_app_rx.viewmodel

import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.example.android_weather_app_rx.model.UseCase
import com.example.android_weather_app_rx.model.Weather
import com.example.android_weather_app_rx.network.WeatherForLocation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class ViewModel(private val useCase: UseCase) : ViewModel() {

    val viewStateObservable = BehaviorSubject.create<ViewState>()

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private var viewState = ViewState()
    private val compositeDisposable = CompositeDisposable()

    fun startApplication() {
        compositeDisposable.add(
            useCase.getWeatherForLocation(44418)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { weatherForLocation -> onSuccess(weatherForLocation) },
                    { error -> onFailure(error.localizedMessage) })
        )

        viewState = viewState.copy(
            isLoadingDialog = true,
            viewOfText = View.GONE
        )
        invalidateView()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    private fun onSuccess(weather: Weather) {
        viewState = viewState.copy(
            isLoadingDialog = false,
            didCallFail = false,
            viewOfText = View.VISIBLE,
            cityTitle = weather.cityTitle,
            title = weather.title,
            theTemp = weather.theTemp,
            airPressure = weather.airPressure,
            humidity = weather.humidity,
            windSpeed = weather.windSpeed
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
        viewStateObservable.onNext(viewState)
    }
}
