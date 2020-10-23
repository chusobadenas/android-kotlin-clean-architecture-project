package com.jesusbadenas.kotlin_clean_architecture_project.data.util

import com.jesusbadenas.kotlin_clean_architecture_project.data.api.response.UserResponse
import com.jesusbadenas.kotlin_clean_architecture_project.domain.model.User

fun UserResponse.toUser() = User(
    userId = userId,
    coverUrl = coverUrl,
    fullName = fullName,
    email = email,
    description = description,
    followers = followers
)
