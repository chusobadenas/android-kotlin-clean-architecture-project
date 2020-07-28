package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseViewModel
import com.jesusbadenas.kotlin_clean_architecture_project.domain.common.DefaultSubscriber
import com.jesusbadenas.kotlin_clean_architecture_project.domain.common.UseCase
import com.jesusbadenas.kotlin_clean_architecture_project.domain.entities.UserEntity
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserEntityMapper

class UserDetailsViewModel
constructor(
    private val getUserDetailsUseCase: UseCase<UserEntity>,
    private val userEntityMapper: UserEntityMapper
) : BaseViewModel() {

    val user: MutableLiveData<User> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        getUserDetailsUseCase.unsubscribe()
    }

    fun loadUserDetails(userId: Int) {
        showRetry(View.GONE)
        showLoading(View.VISIBLE)
        val params: Map<String, Int> = hashMapOf("id" to userId)
        getUserDetailsUseCase.execute(UserDetailsSubscriber(), params)
    }

    private fun showUserDetailsInView(userEntity: UserEntity) {
        user.value = userEntityMapper.mapFrom(userEntity)
    }

    private inner class UserDetailsSubscriber : DefaultSubscriber<UserEntity>() {

        override fun onError(throwable: Throwable) {
            showLoading(View.GONE)
            showError(throwable, "Error loading user details", null, null)
            showRetry(View.VISIBLE)
        }

        override fun onNext(user: UserEntity) {
            showLoading(View.GONE)
            showUserDetailsInView(user)
        }
    }
}
