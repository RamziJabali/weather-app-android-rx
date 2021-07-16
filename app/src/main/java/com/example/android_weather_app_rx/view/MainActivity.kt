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


    private lateinit var retrofit: Retrofit
    private lateinit var jsonWeatherAPI: JsonWeatherAPI

    private lateinit var loadingDialog: LoadingDialog

    private lateinit var viewModel: ViewModel

    private lateinit var windPressureText: TextView
    private lateinit var cityTextView: TextView
    private lateinit var stateTextView: TextView
    private lateinit var temperatureTextView: TextView
    private lateinit var pressureValueTextView: TextView
    private lateinit var humidityValueTextView: TextView
    private lateinit var windSpeedValueTextView: TextView

    private lateinit var humidityTextView: TextView
    private lateinit var windSpeedTextView: TextView
    private lateinit var onFailureTextView: TextView
    private lateinit var weatherImageView: ImageView


    companion object {
        const val API_BASE_URL = "https://www.metaweather.com/"
    }


    override fun setNewViewState(viewState: ViewState) {
        if (viewState.isLoadingDialog) loadingDialog.show() else loadingDialog.hide()
        if (viewState.didCallFail) onFailureTextView.visibility = View.VISIBLE else onFailureTextView.visibility = View.GONE
        setTextAndImageViewVisibility(viewState.viewOfText)
        populateViewsWithText(viewState)
        return
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpFieldMembers()
        viewModel.startApplication()
    }

    private fun setUpFieldMembers() {
        viewModel = ViewModel(this)

        loadingDialog = LoadingDialog(this)

        cityTextView = findViewById(R.id.city)
        stateTextView = findViewById(R.id.state)
        temperatureTextView = findViewById(R.id.temperature)
        pressureValueTextView = findViewById(R.id.pressureValueText)
        humidityValueTextView = findViewById(R.id.humidityValueText)
        windSpeedValueTextView = findViewById(R.id.windSpeedValueText)
        windPressureText = findViewById(R.id.pressureText)
        humidityTextView = findViewById(R.id.humidityText)
        windSpeedTextView = findViewById(R.id.windSpeedText)
        onFailureTextView = findViewById(R.id.onFailureText)
        weatherImageView = findViewById(R.id.weatherImage)
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

