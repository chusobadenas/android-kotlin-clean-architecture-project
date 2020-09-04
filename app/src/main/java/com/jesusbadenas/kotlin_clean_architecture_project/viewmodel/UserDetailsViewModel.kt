package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseViewModel
import com.jesusbadenas.kotlin_clean_architecture_project.data.common.Resource
import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserDataMapper
import kotlinx.coroutines.launch

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
        viewModelScope.launch {
            val userDataResource = userRepository.user(userId)
            updateUser(userDataResource)
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
