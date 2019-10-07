package com.jesusbadenas.kotlin_clean_architecture_project.di.modules

import android.content.Context
import com.jesusbadenas.kotlin_clean_architecture_project.App
import com.jesusbadenas.kotlin_clean_architecture_project.data.api.APIService
import com.jesusbadenas.kotlin_clean_architecture_project.data.api.Network
import com.jesusbadenas.kotlin_clean_architecture_project.data.repositories.UserDataRepository
import com.jesusbadenas.kotlin_clean_architecture_project.di.ApplicationContext
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
class ApplicationModule {

    @ApplicationContext
    @Provides
    @Singleton
    internal fun provideContext(application: App): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideApiService(): APIService {
        return Network.newAPIService()
    }

    @Provides
    @Singleton
    internal fun provideUserRepository(userDataRepository: UserDataRepository): UserRepository {
        return userDataRepository
    }
}
