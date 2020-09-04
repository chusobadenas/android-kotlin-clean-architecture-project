package com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories

import com.jesusbadenas.kotlin_clean_architecture_project.data.api.APIService
import com.jesusbadenas.kotlin_clean_architecture_project.data.common.Resource
import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * [UserRepository] for retrieving user data.
 */
class UserRepository(private val apiService: APIService) {

    suspend fun users(): Resource<List<UserData>> =
        withContext(Dispatchers.IO) {
            try {
                val list: List<UserData> = apiService.userDataList()
                Resource.Success(list)
            } catch (exception: Exception) {
                Resource.Error(exception)
            }
        }

    suspend fun user(userId: Int): Resource<UserData> =
        withContext(Dispatchers.IO) {
            try {
                val user: UserData = apiService.userDataById(userId)
                Resource.Success(user)
            } catch (exception: Exception) {
                Resource.Error(exception)
            }
        }
}
