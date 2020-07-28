package com.jesusbadenas.kotlin_clean_architecture_project.data.repositories

import com.jesusbadenas.kotlin_clean_architecture_project.data.api.APIService
import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.mappers.UserDataMapper
import com.jesusbadenas.kotlin_clean_architecture_project.domain.entities.UserEntity
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import io.reactivex.Observable

/**
 * [UserRepository] for retrieving user data.
 */
class UserDataRepository
constructor(
    private val apiService: APIService,
    private val userDataMapper: UserDataMapper
) : UserRepository {

    override fun users(): Observable<List<UserEntity>> {
        return apiService.userDataList()
            .map { userEntities ->
                userDataMapper.transform(userEntities)
            }
    }

    override fun user(userId: Int): Observable<UserEntity> {
        return apiService.userDataById(userId)
            .map { userEntity ->
                userDataMapper.transform(userEntity)
            }
    }
}
