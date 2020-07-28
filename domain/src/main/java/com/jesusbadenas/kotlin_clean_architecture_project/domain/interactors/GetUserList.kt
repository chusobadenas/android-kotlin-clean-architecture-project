package com.jesusbadenas.kotlin_clean_architecture_project.domain.interactors

import com.jesusbadenas.kotlin_clean_architecture_project.domain.common.UseCase
import com.jesusbadenas.kotlin_clean_architecture_project.domain.entities.UserEntity
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import io.reactivex.Observable

/**
 * This class is an implementation of [UseCase] that represents a use case for
 * retrieving a collection of all [UserEntity].
 */
class GetUserList
constructor(private val userRepository: UserRepository) : UseCase<List<UserEntity>>() {

    override fun create(data: Map<String, Any>?): Observable<List<UserEntity>> {
        return userRepository.users()
    }
}
