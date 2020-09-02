package com.jesusbadenas.kotlin_clean_architecture_project.data.api

import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * APIService for retrieving data from the network using Retrofit2
 */
interface APIService {

    companion object {
        private const val USER_ID = "userId"
    }

    @GET("/android10/Sample-Data/master/Android-CleanArchitecture/users.json")
    suspend fun userDataList(): List<UserData>

    @GET("/android10/Sample-Data/master/Android-CleanArchitecture/user_{$USER_ID}.json")
    suspend fun userDataById(@Path(USER_ID) userId: Int): UserData
}
