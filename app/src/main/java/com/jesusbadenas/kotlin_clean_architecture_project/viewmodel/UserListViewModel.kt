package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseViewModel
import com.jesusbadenas.kotlin_clean_architecture_project.common.Event
import com.jesusbadenas.kotlin_clean_architecture_project.domain.common.DefaultSubscriber
import com.jesusbadenas.kotlin_clean_architecture_project.domain.common.UseCase
import com.jesusbadenas.kotlin_clean_architecture_project.domain.entities.UserEntity
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserEntityMapper
import javax.inject.Inject

class UserListViewModel
@Inject
constructor(
    val getUserListUseCase: UseCase<List<UserEntity>>,
    val userEntityMapper: UserEntityMapper
) : BaseViewModel() {

    private val userList: MutableLiveData<List<User>> = MutableLiveData()
    private val userClicked: MutableLiveData<Event<User>> = MutableLiveData()

    fun getUserList(): LiveData<List<User>> {
        return userList
    }

    fun getUserClicked(): LiveData<Event<User>> {
        return userClicked
    }

    override fun onCleared() {
        super.onCleared()
        getUserListUseCase.unsubscribe()
    }

    fun loadUserList() {
        showRetry(false)
        showLoading(true)
        getUserListUseCase.execute(UserListSubscriber(), null)
    }

    private fun showUsersCollectionInView(userEntities: List<UserEntity>) {
        userList.value = userEntityMapper.mapFrom(userEntities)
    }

    fun onUserClicked(user: User) {
        userClicked.value = Event(user)
    }

    private inner class UserListSubscriber : DefaultSubscriber<List<UserEntity>>() {

        override fun onError(throwable: Throwable) {
            showLoading(false)
            // TODO: showErrorMessage(throwable, "Error loading user list", null)
            showRetry(true)
        }

        override fun onNext(users: List<UserEntity>) {
            showLoading(false)
            showUsersCollectionInView(users)
        }
    }
}
