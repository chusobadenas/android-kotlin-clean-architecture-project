package com.jesusbadenas.kotlin_clean_architecture_project.domain.interactors

import com.jesusbadenas.kotlin_clean_architecture_project.domain.common.UseCase
import com.jesusbadenas.kotlin_clean_architecture_project.domain.entities.UserEntity
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import io.reactivex.Observable

/**
 * This class is an implementation of [UseCase] that represents a use case for
 * retrieving data related to an specific [UserEntity].
 */
class GetUserDetails
constructor(private val userRepository: UserRepository) : UseCase<UserEntity>() {

    override fun create(data: Map<String, Any>?): Observable<UserEntity> {
        val userId = data?.get("id") as Int
        return userRepository.user(userId)
    }
}
