package com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories

import com.jesusbadenas.kotlin_clean_architecture_project.domain.entities.UserEntity
import io.reactivex.Observable

/**
 * Interface that represents a Repository for getting [UserEntity] related data.
 */
interface UserRepository {

    fun users(): Observable<List<UserEntity>>

    fun user(userId: Int): Observable<UserEntity>
}
