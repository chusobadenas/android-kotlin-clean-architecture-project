package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseViewModel
import com.jesusbadenas.kotlin_clean_architecture_project.data.common.Resource
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserDataMapper

class UserDetailsViewModel(
    userId: Int,
    userRepository: UserRepository,
    private val userDataMapper: UserDataMapper
) : BaseViewModel() {

    init {
        showLoading(View.VISIBLE)
        showRetry(View.GONE)
    }

    val user: LiveData<User> =
        Transformations.map(userRepository.user(userId)) { userDataResource ->
            when (userDataResource) {
                is Resource.Error -> {
                    showError(userDataResource.throwable, "Error loading user details")
                    null
                }
                is Resource.Success -> {
                    userDataMapper.mapFrom(userDataResource.data!!)
                }
            }
        }
}
