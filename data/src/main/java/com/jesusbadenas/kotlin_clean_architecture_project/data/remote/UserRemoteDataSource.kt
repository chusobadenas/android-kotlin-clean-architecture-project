package com.jesusbadenas.kotlin_clean_architecture_project.data.remote

import com.jesusbadenas.kotlin_clean_architecture_project.data.api.model.UserDTO

interface UserRemoteDataSource {

    suspend fun getUsers(): List<UserDTO>

    suspend fun getUser(userId: Int): UserDTO?
}
