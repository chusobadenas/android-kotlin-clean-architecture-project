package com.jesusbadenas.kotlin_clean_architecture_project.presentation.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jesusbadenas.kotlin_clean_architecture_project.domain.model.User
import com.jesusbadenas.kotlin_clean_architecture_project.domain.usecase.GetUsersUseCase
import com.jesusbadenas.kotlin_clean_architecture_project.presentation.R
import com.jesusbadenas.kotlin_clean_architecture_project.presentation.common.BaseViewModel

class UserListViewModel(
    private val getUsersUseCase: GetUsersUseCase
) : BaseViewModel() {

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>>
        get() = _userList

    fun loadUserList() {
        getUsersUseCase.invoke(
            scope = viewModelScope,
            onError = { throwable ->
                showError(throwable) {
                    onRetryAction()
                }
            },
            onResult = { list ->
                if (list.isEmpty()) {
                    showError(messageTextId = R.string.error_message_empty_list) {
                        onRetryAction()
                    }
                } else {
                    showLoading(false)
                    _userList.value = list
                }
            }
        )
    }
}
