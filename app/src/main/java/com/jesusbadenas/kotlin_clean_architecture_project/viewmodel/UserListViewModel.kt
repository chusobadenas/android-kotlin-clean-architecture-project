package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseViewModel
import com.jesusbadenas.kotlin_clean_architecture_project.common.Resource
import com.jesusbadenas.kotlin_clean_architecture_project.domain.common.DefaultSubscriber
import com.jesusbadenas.kotlin_clean_architecture_project.domain.common.UseCase
import com.jesusbadenas.kotlin_clean_architecture_project.domain.entities.UserEntity
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserEntityMapper

class UserListViewModel
constructor(
    private val getUserListUseCase: UseCase<List<UserEntity>>,
    private val userEntityMapper: UserEntityMapper
) : BaseViewModel() {

    val userList: MutableLiveData<List<User>> = MutableLiveData()
    val userClicked: MutableLiveData<Resource<User>> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        getUserListUseCase.unsubscribe()
    }

    fun loadUserList() {
        showRetry(View.GONE)
        showLoading(View.VISIBLE)
        getUserListUseCase.execute(UserListSubscriber(), null)
    }

    private fun showUsersCollectionInView(userEntities: List<UserEntity>) {
        userList.value = userEntityMapper.mapFrom(userEntities)
    }

    fun onUserClicked(user: User) {
        userClicked.value = Resource.Success(user)
    }

    private inner class UserListSubscriber : DefaultSubscriber<List<UserEntity>>() {

        override fun onError(throwable: Throwable) {
            showLoading(View.GONE)
            showError(throwable, "Error loading user list", null, null)
            showRetry(View.VISIBLE)
        }

        override fun onNext(users: List<UserEntity>) {
            showLoading(View.GONE)
            showUsersCollectionInView(users)
        }
    }
}
