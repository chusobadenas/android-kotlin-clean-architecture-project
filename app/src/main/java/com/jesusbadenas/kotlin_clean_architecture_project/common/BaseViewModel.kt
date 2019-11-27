package com.jesusbadenas.kotlin_clean_architecture_project.common

import android.content.DialogInterface
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val isRetry: MutableLiveData<Boolean> = MutableLiveData()
    private val hasError: MutableLiveData<Event<UIError>> = MutableLiveData()

    fun isLoading(): LiveData<Boolean> {
        return isLoading
    }

    fun isRetry(): LiveData<Boolean> {
        return isRetry
    }

    fun hasError(): LiveData<Event<UIError>> {
        return hasError
    }

    fun showLoading(loading: Boolean) {
        isLoading.value = loading
    }

    fun showRetry(retry: Boolean) {
        isRetry.value = retry
    }

    fun showError(
        throwable: Throwable,
        logMessage: String,
        errorMsgId: Int?,
        action: DialogInterface.OnClickListener?
    ) {
        hasError.value = Event(UIError(throwable, logMessage, errorMsgId, action))
    }
}
