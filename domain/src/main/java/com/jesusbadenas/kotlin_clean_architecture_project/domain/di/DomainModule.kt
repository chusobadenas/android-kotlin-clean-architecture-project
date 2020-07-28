package com.jesusbadenas.kotlin_clean_architecture_project.domain.di

import com.jesusbadenas.kotlin_clean_architecture_project.domain.common.UseCase
import com.jesusbadenas.kotlin_clean_architecture_project.domain.entities.UserEntity
import com.jesusbadenas.kotlin_clean_architecture_project.domain.interactors.GetUserDetails
import com.jesusbadenas.kotlin_clean_architecture_project.domain.interactors.GetUserList
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {
    factory<UseCase<UserEntity>>(qualifier = named("getUserDetails")) { GetUserDetails(get()) }
    factory<UseCase<List<UserEntity>>>(qualifier = named("getUserList")) { GetUserList(get()) }
}
