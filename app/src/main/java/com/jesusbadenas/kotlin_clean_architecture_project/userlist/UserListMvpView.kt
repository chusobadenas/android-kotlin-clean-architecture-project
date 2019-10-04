package com.jesusbadenas.kotlin_clean_architecture_project.userlist

import com.jesusbadenas.kotlin_clean_architecture_project.common.MvpView
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of [User].
 */
interface UserListMvpView : MvpView {

    fun renderUserList(userCollection: Collection<User>)

    fun viewUser(user: User)
}
