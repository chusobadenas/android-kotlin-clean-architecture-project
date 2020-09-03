package com.jesusbadenas.kotlin_clean_architecture_project.common

import android.content.DialogInterface
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val containerVisibility = MutableLiveData<Int>()
    val loadingVisibility = MutableLiveData<Int>()
    val retryVisibility = MutableLiveData<Int>()
    val uiError = MutableLiveData<UIError>()

    val retryAction = LiveEvent<Nothing>()

    fun showLoading(loadingVisibility: Int) {
        this.loadingVisibility.value = loadingVisibility
        this.containerVisibility.value =
            if (loadingVisibility == View.VISIBLE) View.GONE else View.VISIBLE
    }

    fun showRetry(retryVisibility: Int) {
        this.retryVisibility.value = retryVisibility
        this.containerVisibility.value =
            if (retryVisibility == View.VISIBLE) View.GONE else View.VISIBLE
    }

    fun showError(
        throwable: Throwable,
        logMessage: String,
        errorMsgId: Int? = null,
        action: DialogInterface.OnClickListener? = null
    ) {
        showLoading(View.GONE)
        showRetry(View.VISIBLE)
        uiError.value = UIError(throwable, logMessage, errorMsgId, action)
    }

    fun onRetryButtonClick() {
        showRetry(View.GONE)
        showLoading(View.VISIBLE)
        retryAction.call()
    }
}
