package com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.jesusbadenas.kotlin_clean_architecture_project.data.api.APIService
import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import kotlinx.coroutines.Dispatchers

/**
 * [UserRepository] for retrieving user data.
 */
class UserRepository(
    private val apiService: APIService
) {

    fun users(): LiveData<List<UserData>> =
        liveData(Dispatchers.IO) {
            emit(apiService.userDataList())
        }

    fun user(userId: Int): LiveData<UserData> =
        liveData(Dispatchers.IO) {
            emit(apiService.userDataById(userId))
        }
}
