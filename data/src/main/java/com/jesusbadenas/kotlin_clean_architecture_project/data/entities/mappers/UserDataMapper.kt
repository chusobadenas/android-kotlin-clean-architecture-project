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
    fun transform(userData: UserData?): UserEntity? {
        var userEntity: UserEntity? = null
        if (userData != null) {
            userEntity = UserEntity(
                userData.userId,
                userData.coverUrl,
                userData.fullName,
                userData.email,
                userData.description,
                userData.followers
            )
        }
        return userEntity
    }

    fun transform(userDataCollection: Collection<UserData>): List<UserEntity> {
        val userList = ArrayList<UserEntity>()
        for (userData in userDataCollection) {
            val user = transform(userData)
            if (user != null) {
                userList.add(user)
            }
        }
        return userList
    }
}
