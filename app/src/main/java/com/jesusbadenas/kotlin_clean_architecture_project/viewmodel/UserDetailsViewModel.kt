package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseViewModel
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserDataMapper

class UserDetailsViewModel(
    private val userId: Int,
    private val userRepository: UserRepository,
    private val userDataMapper: UserDataMapper
) : BaseViewModel() {

    val user = MutableLiveData<User>()

    init {
        loadUser()
    }

    fun loadUser() {
        viewModelScope.safeLaunch {
            val userData = userRepository.user(userId)
            user.value = userDataMapper.mapFrom(userData)
        }
    }
}
