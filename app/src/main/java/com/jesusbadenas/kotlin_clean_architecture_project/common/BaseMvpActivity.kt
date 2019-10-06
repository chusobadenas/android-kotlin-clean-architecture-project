package com.jesusbadenas.kotlin_clean_architecture_project.common

import android.content.Context
import android.content.DialogInterface
import com.jesusbadenas.kotlin_clean_architecture_project.R

/**
 * [BaseActivity] class for MVP views.
 */
abstract class BaseMvpActivity : BaseActivity(), MvpView {

    override fun showLoading() {
        // No-op by default
    }

    override fun hideLoading() {
        // No-op by default
    }

    override fun showRetry() {
        // No-op by default
    }

    override fun hideRetry() {
        // No-op by default
    }

    override fun showError(message: String?, action: DialogInterface.OnClickListener?) {
        val title = getString(R.string.error_title_generic)
        DialogFactory.showDialog(
            this,
            DialogFactory.DialogType.SIMPLE,
            title,
            message,
            android.R.string.ok,
            action
        )
    }

    override fun context(): Context {
        return this
    }
}
