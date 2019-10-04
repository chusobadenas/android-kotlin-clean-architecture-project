package com.jesusbadenas.kotlin_clean_architecture_project.data.entities.mappers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import javax.inject.Inject

/**
 * Class used to transform from Strings representing json to valid objects.
 */
class UserDataJsonMapper
@Inject
constructor() {

    private val gson: Gson = Gson()

    fun transformUserData(userJsonResponse: String): UserData {
        val userEntityType = object : TypeToken<UserData>() {
        }.type
        return this.gson.fromJson<UserData>(userJsonResponse, userEntityType)
    }

    fun transformUserDataCollection(userListJsonResponse: String): List<UserData>? {
        val listOfUserEntityType = object : TypeToken<List<UserData>>() {
        }.type
        return this.gson.fromJson<List<UserData>>(userListJsonResponse, listOfUserEntityType)
    }
}
