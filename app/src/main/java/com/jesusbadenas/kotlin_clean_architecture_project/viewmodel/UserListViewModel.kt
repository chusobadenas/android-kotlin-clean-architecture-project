package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseViewModel
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

    private fun failure(exception: Exception) {
        showLoading(View.GONE)
        showError(exception, "Error loading user list", null, null)
        showRetry(View.VISIBLE)
    }

    private fun success(users: List<UserData>) {
        showLoading(View.GONE)
        userList.value = users.map { userDataMapper.mapFrom(it) }
    }

    fun loadUserList() {
        showRetry(View.GONE)
        showLoading(View.VISIBLE)
        viewModelScope.launch {
            try {
                success(userRepository.users())
            } catch (exception: Exception) {
                failure(exception)
            }
        }
    }
}
