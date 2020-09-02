package com.jesusbadenas.kotlin_clean_architecture_project.data.di

import com.jesusbadenas.kotlin_clean_architecture_project.data.api.Network
import org.koin.dsl.module

val dataModule = module {
    single { Network.newAPIService() }
}
