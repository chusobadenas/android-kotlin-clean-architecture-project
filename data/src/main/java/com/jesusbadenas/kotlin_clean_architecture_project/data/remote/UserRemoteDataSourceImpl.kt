package com.jesusbadenas.kotlin_clean_architecture_project.data.remote

import com.jesusbadenas.kotlin_clean_architecture_project.data.api.UsersAPI
import com.jesusbadenas.kotlin_clean_architecture_project.data.api.model.UserDTO

class UserRemoteDataSourceImpl(
    private val usersApi: UsersAPI
) : UserRemoteDataSource {

    override suspend fun getUsers(): List<UserDTO> = usersApi.users()

    override suspend fun getUser(userId: Int): UserDTO? = usersApi.user(userId)
}
