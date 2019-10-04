package com.jesusbadenas.kotlin_clean_architecture_project.domain.common

import io.reactivex.observers.DisposableObserver

/**
 * Default subscriber base class to be used whenever you want default error handling.
 */
open class DefaultSubscriber<T> : DisposableObserver<T>() {

    override fun onComplete() {
        // no-op by default.
    }

    override fun onError(throwable: Throwable) {
        // no-op by default.
    }

    override fun onNext(t: T) {
        // no-op by default.
    }
}
