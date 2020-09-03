package com.jesusbadenas.kotlin_clean_architecture_project.data.api

import com.google.gson.GsonBuilder
import com.jesusbadenas.kotlin_clean_architecture_project.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Network instance
 */
object Network {

    private const val API_BASE_URL = "https://raw.githubusercontent.com/"
    private const val CONNECT_TIMEOUT = 15000
    private const val READ_TIMEOUT = 20000
    private const val WRITE_TIMEOUT = 20000

    private fun createHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()

        // Enable logging
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
            clientBuilder.addInterceptor(interceptor)
        }

        // Interceptors
        clientBuilder.apply {
            addInterceptor(Interceptors.internalServerError())
            addInterceptor(Interceptors.networkError())
        }

        // Timeouts
        return clientBuilder.apply {
            connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            readTimeout(READ_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
        }.build()
    }

    fun newAPIService(): APIService {
        val gson = GsonBuilder().apply {
            setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        }.create()

        val retrofit = Retrofit.Builder().apply {
            baseUrl(API_BASE_URL)
            client(createHttpClient())
            addConverterFactory(GsonConverterFactory.create(gson))
        }.build()

        return retrofit.create(APIService::class.java)
    }
}
