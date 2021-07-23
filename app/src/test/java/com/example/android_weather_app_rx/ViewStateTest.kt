package com.example.android_weather_app_rx

import android.view.View
import com.example.android_weather_app_rx.view.MainActivity
import io.mockk.mockk
import org.junit.Assert.assertTrue
import org.junit.Test

class ViewStateTest {
    private val mainActivity: MainActivity by lazy {
        mockk(relaxed = true)
    }

    @Test
    fun stateTextTest() {
        with(mainActivity) {
            assertTrue(stateTextView.toString().isNotEmpty())
        }
    }

    @Test
    fun cityTextTest() {
        with(mainActivity) {
            assertTrue(cityTextView.toString().isNotEmpty())
        }
    }

    @Test
    fun windPressureTextTest() {
        with(mainActivity) {
            assertTrue(pressureValueTextView.toString().isNotEmpty())
        }
    }

    @Test
    fun temperatureTextTest() {
        with(mainActivity) {
            assertTrue(temperatureTextView.toString().isNotEmpty())
        }
    }

    @Test
    fun humidityTextTest() {
        with(mainActivity) {
            assertTrue(humidityValueTextView.toString().isNotEmpty())
        }
    }

    @Test
    fun windSpeedTextTest() {
        with(mainActivity) {
            assertTrue(windSpeedValueTextView.toString().isNotEmpty())
        }
    }

    @Test
    fun imageVisibilityTest() {
        with(mainActivity) {
            assertTrue(weatherImageView.visibility == View.VISIBLE)
        }
    }
}