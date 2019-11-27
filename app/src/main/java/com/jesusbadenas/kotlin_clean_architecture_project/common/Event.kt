package com.jesusbadenas.kotlin_clean_architecture_project.common

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class Event<out T>(private val content: T) {

    /**
     * Returns the content
     */
    fun peekContent(): T = content
}
