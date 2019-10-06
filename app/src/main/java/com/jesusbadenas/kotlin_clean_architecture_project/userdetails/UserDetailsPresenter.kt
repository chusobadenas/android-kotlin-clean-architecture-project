package com.jesusbadenas.kotlin_clean_architecture_project.userdetails

import com.jesusbadenas.kotlin_clean_architecture_project.common.BasePresenter
import com.jesusbadenas.kotlin_clean_architecture_project.di.PerActivity
import com.jesusbadenas.kotlin_clean_architecture_project.domain.common.DefaultSubscriber
import com.jesusbadenas.kotlin_clean_architecture_project.domain.common.UseCase
import com.jesusbadenas.kotlin_clean_architecture_project.domain.entities.UserEntity
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserEntityMapper
import javax.inject.Inject

/**
 * [BasePresenter] that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
class UserDetailsPresenter
@Inject
constructor(
    private val getUserDetailsUseCase: UseCase<UserEntity>,
    private val userEntityMapper: UserEntityMapper
) : BasePresenter<UserDetailsMvpView>() {

    fun initialize(userId: Int) {
        checkViewAttached()
        loadUserDetails(userId)
    }

    override fun detachView() {
        super.detachView()
        getUserDetailsUseCase.unsubscribe()
    }

    private fun loadUserDetails(userId: Int) {
        mvpView?.hideRetry()
        mvpView?.showLoading()
        getUserDetails(userId)
    }

    private fun showUserDetailsInView(userEntity: UserEntity) {
        val user = userEntityMapper.mapFrom(userEntity)
        mvpView?.renderUser(user)
    }

    private fun getUserDetails(userId: Int) {
        val params: Map<String, Int> = hashMapOf("id" to userId)
        getUserDetailsUseCase.execute(UserDetailsSubscriber(), params)
    }

    private inner class UserDetailsSubscriber : DefaultSubscriber<UserEntity>() {

        override fun onError(throwable: Throwable) {
            mvpView?.hideLoading()
            showErrorMessage(throwable, "Error loading user details", null)
            mvpView?.showRetry()
        }

        override fun onNext(user: UserEntity) {
            mvpView?.hideLoading()
            showUserDetailsInView(user)
        }
    }
}
