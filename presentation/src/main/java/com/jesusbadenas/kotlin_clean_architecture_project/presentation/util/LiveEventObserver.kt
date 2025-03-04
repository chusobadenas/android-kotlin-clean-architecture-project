package com.jesusbadenas.kotlin_clean_architecture_project.presentation.util

import androidx.lifecycle.Observer

/**
 * An [Observer] for [LiveEvent]s, simplifying the pattern of checking if the [LiveEvent]'s content has
 * already been handled.
 *
 * [onEventUnhandledContent] is *only* called if the [LiveEvent]'s contents has not been handled.
 */
class LiveEventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) :
    Observer<LiveEvent<T>> {
    override fun onChanged(value: LiveEvent<T>) {
        value.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}
