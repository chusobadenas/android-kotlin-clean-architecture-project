package com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers

import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User

fun UserData.toUser() = User(
    userId = userId,
    coverUrl = coverUrl,
    fullName = fullName,
    email = email,
    description = description,
    followers = followers
)
