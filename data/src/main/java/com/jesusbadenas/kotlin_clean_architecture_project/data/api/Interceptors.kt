package com.jesusbadenas.kotlin_clean_architecture_project.data.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.jesusbadenas.kotlin_clean_architecture_project.data.api.exception.InternalServerErrorException
import com.jesusbadenas.kotlin_clean_architecture_project.data.api.exception.NetworkException
import okhttp3.Interceptor
import org.koin.core.KoinComponent
import org.koin.core.inject


object Interceptors : KoinComponent {

    private val context: Context by inject()

    private const val INTERNAL_SERVER_ERROR = 500

    fun internalServerError(): Interceptor = Interceptor { chain ->
        val request = chain.request()
        val response = chain.proceed(request)
        if (response.code == INTERNAL_SERVER_ERROR) {
            throw InternalServerErrorException()
        }
        response
    }

    private fun isConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = cm.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

    fun networkError() = Interceptor { chain ->
        val request = chain.request()
        val response = chain.proceed(request)
        if (!isConnected()) {
            throw NetworkException()
        }
        response
    }
}
