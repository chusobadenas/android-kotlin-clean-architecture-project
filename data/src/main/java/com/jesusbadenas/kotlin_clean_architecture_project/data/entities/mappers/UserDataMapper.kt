package com.jesusbadenas.kotlin_clean_architecture_project.data.entities.mappers

import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import com.jesusbadenas.kotlin_clean_architecture_project.domain.entities.UserEntity
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Mapper class used to transform [UserData] (in the data layer) to [UserEntity] in the
 * domain layer.
 */
@Singleton
class UserDataMapper
@Inject
constructor() {
    fun transform(userData: UserData): UserEntity {
        return UserEntity(
            userData.userId,
            userData.coverUrl,
            userData.fullName,
            userData.email,
            userData.description,
            userData.followers
        )
    }

    fun transform(userDataList: List<UserData>): List<UserEntity> {
        val userList = ArrayList<UserEntity>()
        for (userData in userDataList) {
            val user = transform(userData)
            userList.add(user)
        }
        return userList
    }
}
