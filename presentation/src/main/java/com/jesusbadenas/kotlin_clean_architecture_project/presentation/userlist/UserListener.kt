package com.jesusbadenas.kotlin_clean_architecture_project.presentation.userlist

import com.jesusbadenas.kotlin_clean_architecture_project.domain.model.User

interface UserListener {
    fun onUserItemClicked(user: User)
}
