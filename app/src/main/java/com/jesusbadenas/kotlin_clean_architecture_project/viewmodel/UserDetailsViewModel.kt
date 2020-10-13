package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseViewModel
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.toUser

class UserDetailsViewModel(
    private val userId: Int,
    private val userRepository: UserRepository
) : BaseViewModel() {

    val user = MutableLiveData<User>()

    init {
        loadUser()
    }

    fun loadUser() {
        viewModelScope.safeLaunch {
            user.value = userRepository.user(userId).toUser()
        }
    }
}
