package com.jesusbadenas.kotlin_clean_architecture_project.data.repository

import com.jesusbadenas.kotlin_clean_architecture_project.data.local.UserLocalDataSource
import com.jesusbadenas.kotlin_clean_architecture_project.data.remote.UserRemoteDataSource
import com.jesusbadenas.kotlin_clean_architecture_project.data.util.toUser
import com.jesusbadenas.kotlin_clean_architecture_project.data.util.toUserEntity
import com.jesusbadenas.kotlin_clean_architecture_project.domain.model.User
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repository.UserRepository
import com.jesusbadenas.kotlin_clean_architecture_project.domain.util.toList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun getUsers(): List<User> = withContext(Dispatchers.IO) {
        // Get from database first
        val localUsers = userLocalDataSource.getUsers()
        if (localUsers.isEmpty()) {
            // If not found, get from server
            val remoteUsers = userRemoteDataSource.getUsers().map { it.toUser() }
            val entities = remoteUsers.map { it.toUserEntity() }
            userLocalDataSource.insertUsers(entities)
            remoteUsers
        } else {
            // Return database users
            localUsers.map { it.toUser() }
        }
    }

    override suspend fun getUser(userId: Int): User? = withContext(Dispatchers.IO) {
        // Get from database first
        userLocalDataSource.getUser(userId)?.toUser()
        // If not found, get from server
            ?: userRemoteDataSource.getUser(userId)?.toUser()?.also { user ->
                userLocalDataSource.insertUsers(user.toUserEntity().toList())
            }
    }
}
