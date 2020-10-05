package com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories

import com.jesusbadenas.kotlin_clean_architecture_project.data.api.APIService
import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * [UserRepository] for retrieving user data.
 */
class UserRepository(private val apiService: APIService) {

    suspend fun users(): List<UserData> =
        withContext(Dispatchers.IO) {
            apiService.userDataList()
        }

    suspend fun user(userId: Int): UserData =
        withContext(Dispatchers.IO) {
            apiService.userDataById(userId)
        }
}
