package com.jesusbadenas.kotlin_clean_architecture_project.common

sealed class Resource<T>(
    val data: T,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(data: T, message: String? = null) : Resource<T>(data, message)
}
