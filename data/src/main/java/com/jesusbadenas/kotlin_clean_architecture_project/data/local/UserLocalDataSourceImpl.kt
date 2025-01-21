package com.jesusbadenas.kotlin_clean_architecture_project.data.local

import com.jesusbadenas.kotlin_clean_architecture_project.data.db.dao.UserDao
import com.jesusbadenas.kotlin_clean_architecture_project.data.db.model.UserEntity

class UserLocalDataSourceImpl(
    private val usersDao: UserDao
) : UserLocalDataSource {

    override suspend fun getUsers(): List<UserEntity> = usersDao.getAll()

    override suspend fun getUser(userId: Int): UserEntity? = usersDao.getById(userId)

    override suspend fun insertUsers(users: List<UserEntity>) {
        usersDao.insert(users)
    }
}
