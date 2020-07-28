package com.jesusbadenas.kotlin_clean_architecture_project.data.di

import com.jesusbadenas.kotlin_clean_architecture_project.data.api.Network
import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.mappers.UserDataMapper
import com.jesusbadenas.kotlin_clean_architecture_project.data.repositories.UserDataRepository
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import org.koin.dsl.module

val dataModule = module {
    single { Network.newAPIService() }
    single { UserDataMapper() }
    single<UserRepository> { UserDataRepository(get(), get()) }
}
