package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseViewModel
import com.jesusbadenas.kotlin_clean_architecture_project.domain.common.DefaultSubscriber
import com.jesusbadenas.kotlin_clean_architecture_project.domain.common.UseCase
import com.jesusbadenas.kotlin_clean_architecture_project.domain.entities.UserEntity
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserEntityMapper
import javax.inject.Inject

class UserDetailsViewModel
@Inject
constructor(
    private val getUserDetailsUseCase: UseCase<UserEntity>,
    private val userEntityMapper: UserEntityMapper
) : BaseViewModel() {

    private val user: MutableLiveData<User> = MutableLiveData()

    fun getUser(): LiveData<User> {
        return user
    }

    override fun onCleared() {
        super.onCleared()
        getUserDetailsUseCase.unsubscribe()
    }

    fun loadUserDetails(userId: Int) {
        showRetry(false)
        showLoading(true)
        val params: Map<String, Int> = hashMapOf("id" to userId)
        getUserDetailsUseCase.execute(UserDetailsSubscriber(), params)
    }

    private fun showUserDetailsInView(userEntity: UserEntity) {
        user.value = userEntityMapper.mapFrom(userEntity)
    }

    private inner class UserDetailsSubscriber : DefaultSubscriber<UserEntity>() {

        override fun onError(throwable: Throwable) {
            showLoading(false)
            showError(throwable, "Error loading user details", null, null)
            showRetry(true)
        }

        override fun onNext(user: UserEntity) {
            showLoading(false)
            showUserDetailsInView(user)
        }
    }
}
