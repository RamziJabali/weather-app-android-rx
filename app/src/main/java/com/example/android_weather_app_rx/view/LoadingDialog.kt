package com.example.android_weather_app_rx.view


import android.app.Activity
import android.app.AlertDialog
import com.example.android_weather_app_rx.R

class LoadingDialog(activity: Activity) {

    private val dialog: AlertDialog

    init {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.loading_screen, null))
        builder.setCancelable(false)
        dialog = builder.create()
    }

    fun show() {
        dialog.show()
    }

    fun hide(){
        dialog.dismiss()
    }

}