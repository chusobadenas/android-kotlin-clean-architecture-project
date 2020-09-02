package com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers

import com.jesusbadenas.kotlin_clean_architecture_project.common.Mapper
import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import com.jesusbadenas.kotlin_clean_architecture_project.entities.User

class UserDataMapper : Mapper<UserData, User>() {

    override fun mapFrom(from: UserData) = User(
        from.userId,
        from.coverUrl,
        from.fullName,
        from.email,
        from.description,
        from.followers
    )

    override fun mapFrom(from: List<UserData>): List<User> {
        val userList: ArrayList<User> = ArrayList()
        if (from.isNotEmpty()) {
            for (userEntity in from) {
                userList.add(mapFrom(userEntity))
            }
        }
        return userList
    }
}
