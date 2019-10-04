package com.jesusbadenas.kotlin_clean_architecture_project.domain.common

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class UseCase<T> {

    private var observer: DisposableObserver<T>? = null

    abstract fun create(data: Map<String, Any>? = null): Observable<T>

    fun execute(observer: DisposableObserver<T>, params: Map<String, Any>?) {
        this.observer = create(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(observer)
    }

    fun unsubscribe() {
        if (this.observer != null && !this.observer!!.isDisposed) {
            observer?.dispose()
        }
    }
}
