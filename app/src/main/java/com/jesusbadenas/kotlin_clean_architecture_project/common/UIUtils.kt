package com.jesusbadenas.kotlin_clean_architecture_project.common

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jesusbadenas.kotlin_clean_architecture_project.R
import com.jesusbadenas.kotlin_clean_architecture_project.di.GlideApp
import timber.log.Timber

/**
 * UI utilities class
 */
object UIUtils {

    fun loadImageUrl(context: Context, view: ImageView, url: String?) {
        // Configure image
        GlideApp.with(context)
            .load(url)
            .centerCrop()
            .placeholder(R.color.bg_light_grey)
            .error(R.color.bg_light_grey)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }

    fun showError(context: Context, uiError: UIError) {
        // Show log message
        Timber.e(uiError.throwable)

        // Show dialog
        val message = uiError.errorMsgId?.let {
            context.getString(it)
        } ?: context.getString(R.string.error_message_generic)

        val title = context.getString(R.string.error_title_generic)

        DialogFactory.showDialog(
            context,
            DialogFactory.DialogType.SIMPLE,
            title,
            message,
            android.R.string.ok,
            uiError.action
        )
    }
}
