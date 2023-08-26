package com.example.weatherapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object Constants {
    const val APP_ID = "514dcdf0cf0d1f6a56706c320baff8cd"
    const val BASE_URL = "https://api.openweathermap.org/data/"
    const val METRIC_UNIT = "metric"


    //Define a function called isNetworkAvailable that takes a Context parameter and returns a Boolean value
    fun isNetworkAvailable(context: Context): Boolean {
        //Get an instance of ConnectivityManager using the context parameter
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        //Check if the device's API is level 23 or higher
        //Get the currently active network and return false if 1there is none
        val network = connectivityManager.activeNetwork ?: return false

        //Get the capabilities of the active network and return false if it has none
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        //Check the type of the active network and return true if it is either WIFI, CELULAR, or ETHERNET
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            else -> return false
        }
    }
}