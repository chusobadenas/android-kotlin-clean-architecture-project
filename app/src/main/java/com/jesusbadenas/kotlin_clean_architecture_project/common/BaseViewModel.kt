package com.jesusbadenas.kotlin_clean_architecture_project.common

import android.content.DialogInterface
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private val containerVisibility: MutableLiveData<Int> = MutableLiveData()
    private val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    private val retryVisibility: MutableLiveData<Int> = MutableLiveData()
    private val uiError: MutableLiveData<Resource<UIError>> = MutableLiveData()

    fun getContainerVisibility(): LiveData<Int> {
        return containerVisibility
    }

    fun getLoadingVisibility(): LiveData<Int> {
        return loadingVisibility
    }

    fun getRetryVisibility(): LiveData<Int> {
        return retryVisibility
    }

    fun getUIError(): LiveData<Resource<UIError>> {
        return uiError
    }

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
        errorMsgId: Int?,
        action: DialogInterface.OnClickListener?
    ) {
        uiError.value = Resource.Error(UIError(throwable, logMessage, errorMsgId, action))
    }
}
