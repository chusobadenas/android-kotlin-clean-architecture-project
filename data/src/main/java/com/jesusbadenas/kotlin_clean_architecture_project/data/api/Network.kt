package com.jesusbadenas.kotlin_clean_architecture_project.data.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import org.koin.core.KoinComponent
import org.koin.core.inject

object Network : KoinComponent {

    private val context: Context by inject()

    fun isConnected(): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        for (network in cm.allNetworks) {
            cm.getNetworkCapabilities(network)?.let {
                if (it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                ) {
                    result = true
                }
            }
        }
        return result
    }
}
