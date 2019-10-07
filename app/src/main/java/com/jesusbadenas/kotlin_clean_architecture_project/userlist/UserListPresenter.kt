package com.jesusbadenas.kotlin_clean_architecture_project.userlist

import com.jesusbadenas.kotlin_clean_architecture_project.common.BasePresenter
import com.jesusbadenas.kotlin_clean_architecture_project.di.PerActivity
import com.jesusbadenas.kotlin_clean_architecture_project.domain.common.DefaultSubscriber
import com.jesusbadenas.kotlin_clean_architecture_project.domain.common.UseCase
import com.jesusbadenas.kotlin_clean_architecture_project.domain.entities.UserEntity
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserEntityMapper
import javax.inject.Inject

/**
 * [Presenter] that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
class UserListPresenter
@Inject
constructor(
    private val getUserListUseCase: UseCase<List<UserEntity>>,
    private val userEntityMapper: UserEntityMapper
) : BasePresenter<UserListMvpView>() {

    fun initialize() {
        checkViewAttached()
        loadUserList()
    }

    override fun detachView() {
        super.detachView()
        getUserListUseCase.unsubscribe()
    }

    private fun loadUserList() {
        mvpView?.hideRetry()
        mvpView?.showLoading()
        getUserList()
    }

    fun onUserClicked(user: User) {
        mvpView?.viewUser(user)
    }

    private fun showUsersCollectionInView(userEntities: List<UserEntity>) {
        val userList = userEntityMapper.mapFrom(userEntities)
        mvpView?.showUserList(userList)
    }

    private fun getUserList() {
        getUserListUseCase.execute(UserListSubscriber(), null)
    }

    private inner class UserListSubscriber : DefaultSubscriber<List<UserEntity>>() {

        override fun onError(throwable: Throwable) {
            mvpView?.hideLoading()
            showErrorMessage(throwable, "Error loading user list", null)
            mvpView?.showRetry()
        }

        override fun onNext(users: List<UserEntity>) {
            mvpView?.hideLoading()
            showUsersCollectionInView(users)
        }
    }
}
