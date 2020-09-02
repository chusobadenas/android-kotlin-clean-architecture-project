package com.jesusbadenas.kotlin_clean_architecture_project.domain.di

import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import org.koin.dsl.module

val domainModule = module {
    single { UserRepository(get()) }
}
