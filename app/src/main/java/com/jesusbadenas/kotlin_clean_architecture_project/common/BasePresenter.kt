package com.jesusbadenas.kotlin_clean_architecture_project.common

import android.content.DialogInterface
import com.jesusbadenas.kotlin_clean_architecture_project.R
import timber.log.Timber

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
open class BasePresenter<T : MvpView> : Presenter<T> {

    var mvpView: T? = null

    private val isViewAttached: Boolean
        get() = mvpView != null

    override fun attachView(mvpView: T) {
        this.mvpView = mvpView
    }

    override fun detachView() {
        mvpView = null
    }

    fun checkViewAttached() {
        if (!isViewAttached) {
            throw MvpViewNotAttachedException()
        }
    }

    private fun showError(
        throwable: Throwable, logMessage: String, errorMsgId: Int?,
        action: DialogInterface.OnClickListener?
    ) {
        // Show log message
        Timber.e(throwable, logMessage)
        // Show dialog
        val message: String?
        if (errorMsgId == null) {
            message = mvpView!!.context().getString(R.string.error_message_generic)
        } else {
            message = mvpView!!.context().getString(errorMsgId)
        }
        mvpView!!.showError(message, action)
    }

    /**
     * Shows an error message in the view
     *
     * @param throwable  the exception
     * @param logMessage the message to show in the logs
     * @param errorMsgId to customize the error message to show (optional)
     */
    fun showErrorMessage(throwable: Throwable, logMessage: String, errorMsgId: Int?) {
        showError(throwable, logMessage, errorMsgId, null)
    }

    /**
     * Shows an error message in the view with an associated action
     *
     * @param throwable  the exception
     * @param logMessage the message to show in the logs
     * @param errorMsgId to customize the error message to show (optional)
     * @param action     the action
     */
    fun showErrorMessage(
        throwable: Throwable, logMessage: String, errorMsgId: Int?,
        action: DialogInterface.OnClickListener
    ) {
        showError(throwable, logMessage, errorMsgId, action)
    }

    private class MvpViewNotAttachedException internal constructor() :
        RuntimeException("Please call Presenter.attachView(MvpView) before requesting data to the Presenter")
}
