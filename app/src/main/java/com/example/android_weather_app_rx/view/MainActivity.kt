package com.example.android_weather_app_rx.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.android_weather_app_rx.R
import com.example.android_weather_app_rx.viewmodel.ViewState
import com.example.android_weather_app_rx.viewmodel.JsonWeatherAPI
import com.example.android_weather_app_rx.viewmodel.RetrofitBuilder
import com.example.android_weather_app_rx.viewmodel.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), ViewListener {


    private val loadingDialog: LoadingDialog by lazy {
        LoadingDialog(this)
    }
    private val viewModel: ViewModel by lazy {
        ViewModel(this)
    }
    private val windPressureText: TextView by lazy {
        findViewById(R.id.pressureText)
    }
    private val cityTextView: TextView by lazy {
        findViewById(R.id.city)
    }
    private val stateTextView: TextView by lazy {
        findViewById(R.id.state)
    }
    private val temperatureTextView: TextView by lazy {
        findViewById(R.id.temperature)
    }
    private val pressureValueTextView: TextView by lazy {
        findViewById(R.id.pressureValueText)
    }
    private val humidityValueTextView: TextView by lazy {
        findViewById(R.id.humidityValueText)
    }
    private val windSpeedValueTextView: TextView by lazy {
        findViewById(R.id.windSpeedValueText)
    }
    private val humidityTextView: TextView by lazy {
        findViewById(R.id.humidityText)
    }
    private val windSpeedTextView: TextView by lazy {
        findViewById(R.id.windSpeedText)
    }
    private val onFailureTextView: TextView by lazy {
        findViewById(R.id.onFailureText)
    }
    private val weatherImageView: ImageView by lazy {
        findViewById(R.id.weatherImage)
    }


    companion object {
        const val API_BASE_URL = "https://www.metaweather.com/"
    }


    override fun setNewViewState(viewState: ViewState) {
        if (viewState.isLoadingDialog) loadingDialog.show() else loadingDialog.hide()
        if (viewState.didCallFail) onFailureTextView.visibility =
            View.VISIBLE else onFailureTextView.visibility = View.GONE
        setTextAndImageViewVisibility(viewState.viewOfText)
        populateViewsWithText(viewState)
        return
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.startApplication()
    }

    private fun setTextAndImageViewVisibility(viewOfText: Int) {
        cityTextView.visibility = viewOfText
        stateTextView.visibility = viewOfText
        temperatureTextView.visibility = viewOfText
        pressureValueTextView.visibility = viewOfText
        humidityValueTextView.visibility = viewOfText
        windSpeedValueTextView.visibility = viewOfText
        windPressureText.visibility = viewOfText
        humidityTextView.visibility = viewOfText
        windSpeedTextView.visibility = viewOfText
        weatherImageView.visibility = viewOfText
    }

    private fun populateViewsWithText(viewState: ViewState) {
        with(viewState) {
            cityTextView.text = cityTitle
            stateTextView.text = title
            temperatureTextView.text = theTemp
            pressureValueTextView.text = airPressure
            humidityValueTextView.text = humidity
            windSpeedValueTextView.text = windSpeed
        }
    }

}

