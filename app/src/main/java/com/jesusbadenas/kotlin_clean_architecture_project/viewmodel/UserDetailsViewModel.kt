package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseViewModel
import com.jesusbadenas.kotlin_clean_architecture_project.data.common.Resource
import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserDataMapper

class UserDetailsViewModel(
    userId: Int,
    userRepository: UserRepository,
    private val userDataMapper: UserDataMapper
) : BaseViewModel() {

    private var userRepositoryValue: LiveData<Resource<UserData>> = userRepository.user(userId)
    val user = MediatorLiveData<User>()

    init {
        user.addSource(userRepositoryValue) { userDataResource ->
            updateUser(userDataResource)
        }
        user.addSource(retryAction) {
            userRepositoryValue = userRepository.user(userId)
        }
    }

    private fun updateUser(userDataResource: Resource<UserData>?) {
        when (userDataResource) {
            is Resource.Error -> {
                showError(userDataResource.throwable, "Error loading user details")
            }
            is Resource.Success -> {
                user.value = userDataMapper.mapFrom(userDataResource.data!!)
            }
        }
    }
}
