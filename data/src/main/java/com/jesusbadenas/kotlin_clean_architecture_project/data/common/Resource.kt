package com.jesusbadenas.kotlin_clean_architecture_project.data.common

sealed class Resource<out T> {
    data class Error(val throwable: Throwable) : Resource<Nothing>()
    data class Success<T>(val data: T?) : Resource<T>()
}
