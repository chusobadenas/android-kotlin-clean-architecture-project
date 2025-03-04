package com.jesusbadenas.kotlin_clean_architecture_project.data.util

import com.jesusbadenas.kotlin_clean_architecture_project.data.api.model.UserDTO
import com.jesusbadenas.kotlin_clean_architecture_project.data.db.model.UserEntity
import com.jesusbadenas.kotlin_clean_architecture_project.domain.model.User

fun UserDTO.toUser() = User(
    id = id,
    email = email,
    imageUrl = "https://thispersondoesnotexist.com/",
    name = name,
    website = website
)

fun UserEntity.toUser() = User(
    id = id,
    email = email,
    imageUrl = imageUrl,
    name = name,
    website = website
)

fun User.toUserEntity() = UserEntity(
    id = id,
    email = email.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
    name = name.orEmpty(),
    website = website
)
