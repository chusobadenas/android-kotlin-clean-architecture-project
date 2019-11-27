package com.jesusbadenas.kotlin_clean_architecture_project.common

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.jesusbadenas.kotlin_clean_architecture_project.R
import dagger.android.support.DaggerFragment
import timber.log.Timber

/**
 * Base [Fragment] class for every fragment in this application.
 */
abstract class BaseFragment : DaggerFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    @TargetApi(23)
    override fun onAttach(context: Context) {
        super.onAttach(context)
        onAttachToContext(context)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity)
        }
    }

    abstract fun onAttachToContext(context: Context)

    fun showError(uiError: UIError) {
        // Show log message
        Timber.e(uiError.throwable, uiError.logMessage)

        // Show dialog
        val message = uiError.errorMsgId?.let {
            context().getString(it)
        } ?: context().getString(R.string.error_message_generic)

        val title = context().getString(R.string.error_title_generic)

        DialogFactory.showDialog(
            context(),
            DialogFactory.DialogType.SIMPLE,
            title,
            message,
            android.R.string.ok,
            uiError.action
        )
    }

    fun context(): Context {
        return activity!!
    }
}
