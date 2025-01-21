package com.jesusbadenas.kotlin_clean_architecture_project.domain.repository

import com.jesusbadenas.kotlin_clean_architecture_project.domain.model.User

interface UserRepository {

    suspend fun getUsers(): List<User>

    suspend fun getUser(userId: Int): User?
}
