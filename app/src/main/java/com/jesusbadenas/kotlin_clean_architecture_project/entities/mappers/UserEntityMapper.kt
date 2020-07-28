package com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers

import com.jesusbadenas.kotlin_clean_architecture_project.domain.common.Mapper
import com.jesusbadenas.kotlin_clean_architecture_project.domain.entities.UserEntity
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User

class UserEntityMapper : Mapper<UserEntity, User>() {

    override fun mapFrom(from: UserEntity) = User(
        from.userId,
        from.coverUrl,
        from.fullName,
        from.email,
        from.description,
        from.followers
    )

    override fun mapFrom(from: List<UserEntity>): List<User> {
        val userList: ArrayList<User> = ArrayList()
        if (from.isNotEmpty()) {
            for (userEntity in from) {
                userList.add(mapFrom(userEntity))
            }
        }
        return userList
    }
}
