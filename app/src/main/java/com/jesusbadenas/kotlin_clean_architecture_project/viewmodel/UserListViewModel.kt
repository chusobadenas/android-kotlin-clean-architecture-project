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

class UserListViewModel(
    private val userRepository: UserRepository,
    private val userDataMapper: UserDataMapper
) : BaseViewModel() {

    val userList = MutableLiveData<List<User>>()

    init {
        loadUserList()
    }

    fun loadUserList() {
        viewModelScope.launch {
            val userDataListResource = userRepository.users()
            updateUserList(userDataListResource)
        }
    }

    private fun updateUserList(userDataListResource: Resource<List<UserData>>?) {
        when (userDataListResource) {
            is Resource.Error -> {
                showError(userDataListResource.throwable, "Error loading user list")
            }
            is Resource.Success -> {
                userList.value = userDataMapper.mapFrom(userDataListResource.data!!)
            }
        }
    }
}
