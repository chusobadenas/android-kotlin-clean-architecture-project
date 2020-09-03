package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseViewModel
import com.jesusbadenas.kotlin_clean_architecture_project.data.common.Resource
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserDataMapper

class UserListViewModel(
    userRepository: UserRepository,
    private val userDataMapper: UserDataMapper
) : BaseViewModel() {

    init {
        showLoading(View.VISIBLE)
        showRetry(View.GONE)
    }

    val userList: LiveData<List<User>> =
        Transformations.map(userRepository.users()) { userDataListResource ->
            when (userDataListResource) {
                is Resource.Error -> {
                    showError(userDataListResource.throwable, "Error loading user list")
                    emptyList()
                }
                is Resource.Success -> {
                    userDataMapper.mapFrom(userDataListResource.data!!)
                }
            }
        }
}
