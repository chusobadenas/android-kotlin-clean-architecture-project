package com.jesusbadenas.kotlin_clean_architecture_project.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val isRetry: MutableLiveData<Boolean> = MutableLiveData()

    fun isLoading(): LiveData<Boolean> {
        return isLoading
    }

    fun isRetry(): LiveData<Boolean> {
        return isRetry
    }

    fun showLoading(loading: Boolean) {
        isLoading.value = loading
    }

    fun showRetry(retry: Boolean) {
        isRetry.value = retry
    }
}
