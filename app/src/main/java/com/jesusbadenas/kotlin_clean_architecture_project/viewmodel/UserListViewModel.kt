package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseViewModel
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.toUser

class UserListViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    val userList = MutableLiveData<List<User>>()

    init {
        loadUserList()
    }

    fun loadUserList() {
        viewModelScope.safeLaunch {
            userList.value = userRepository.users().map { it.toUser() }
        }
    }
}
