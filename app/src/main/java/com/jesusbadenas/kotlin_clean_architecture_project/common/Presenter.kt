package com.jesusbadenas.kotlin_clean_architecture_project.common

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
interface Presenter<V : MvpView> {

    fun attachView(mvpView: V)

    fun detachView()
}
