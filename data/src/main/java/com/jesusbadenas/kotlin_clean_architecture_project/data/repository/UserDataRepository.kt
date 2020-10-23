package com.jesusbadenas.kotlin_clean_architecture_project.data.repository

import com.jesusbadenas.kotlin_clean_architecture_project.data.api.APIService
import com.jesusbadenas.kotlin_clean_architecture_project.data.util.toUser
import com.jesusbadenas.kotlin_clean_architecture_project.domain.model.User
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDataRepository(private val apiService: APIService) : UserRepository {

    override suspend fun users(): List<User> =
        withContext(Dispatchers.IO) {
            apiService.userDataList().map {
                it.toUser()
            }
        }

    override suspend fun user(userId: Int): User =
        withContext(Dispatchers.IO) {
            apiService.userDataById(userId).toUser()
        }
}
