package com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.jesusbadenas.kotlin_clean_architecture_project.data.api.APIService
import com.jesusbadenas.kotlin_clean_architecture_project.data.common.Resource
import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import kotlinx.coroutines.Dispatchers

/**
 * [UserRepository] for retrieving user data.
 */
class UserRepository(
    private val apiService: APIService
) {
    fun users(): LiveData<Resource<List<UserData>>> =
        liveData(Dispatchers.IO) {
            try {
                val list: List<UserData> = apiService.userDataList()
                emit(Resource.Success(list))
            } catch (exception: Exception) {
                emit(Resource.Error(exception))
            }
        }

    fun user(userId: Int): LiveData<Resource<UserData>> =
        liveData(Dispatchers.IO) {
            try {
                val user: UserData = apiService.userDataById(userId)
                emit(Resource.Success(user))
            } catch (exception: Exception) {
                emit(Resource.Error(exception))
            }
        }
}
