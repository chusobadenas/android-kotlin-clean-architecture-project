package com.jesusbadenas.kotlin_clean_architecture_project.data.di

import com.jesusbadenas.kotlin_clean_architecture_project.data.api.UsersAPI
import com.jesusbadenas.kotlin_clean_architecture_project.data.db.dao.UserDao
import com.jesusbadenas.kotlin_clean_architecture_project.data.local.UserLocalDataSource
import com.jesusbadenas.kotlin_clean_architecture_project.data.remote.UserRemoteDataSource
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repository.UserRepository
import io.mockk.mockk
import org.koin.dsl.module

val dataTestModule = module {
    factory { mockk<UsersAPI>() }
    factory { mockk<UserDao>() }
    factory { mockk<UserLocalDataSource>() }
    factory { mockk<UserRemoteDataSource>() }
    factory { mockk<UserRepository>() }
}
