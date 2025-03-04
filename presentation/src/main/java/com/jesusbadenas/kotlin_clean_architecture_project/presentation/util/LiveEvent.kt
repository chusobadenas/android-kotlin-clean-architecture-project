/**
 * Copyright 2019 Hadi Lashkari Ghouchani
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jesusbadenas.kotlin_clean_architecture_project.presentation.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

typealias MutableLiveEvent<T> = MutableLiveData<LiveEvent<T>>
typealias LiveDataEvent<T> = LiveData<LiveEvent<T>>

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class LiveEvent<out T>(private val content: T) {

    private var hasBeenHandled = false

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}
