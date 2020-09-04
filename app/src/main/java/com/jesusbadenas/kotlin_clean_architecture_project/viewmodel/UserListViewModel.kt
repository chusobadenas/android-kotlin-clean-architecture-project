package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseViewModel
import com.jesusbadenas.kotlin_clean_architecture_project.data.common.Resource
import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserDataMapper

class UserListViewModel(
    userRepository: UserRepository,
    private val userDataMapper: UserDataMapper
) : BaseViewModel() {

    private var userListRepositoryValue: LiveData<Resource<List<UserData>>> = userRepository.users()
    val userList = MediatorLiveData<List<User>>()

    init {
        userList.addSource(userListRepositoryValue) { userListDataResource ->
            updateUserList(userListDataResource)
        }
        userList.addSource(retryAction) {
            userListRepositoryValue = userRepository.users()
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
