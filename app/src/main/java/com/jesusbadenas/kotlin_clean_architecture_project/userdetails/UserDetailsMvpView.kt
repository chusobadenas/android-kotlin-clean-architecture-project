package com.jesusbadenas.kotlin_clean_architecture_project.userdetails

import com.jesusbadenas.kotlin_clean_architecture_project.common.MvpView
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a user profile.
 */
interface UserDetailsMvpView : MvpView {

    fun showUserDetails(user: User)
}
