package com.jesusbadenas.kotlin_clean_architecture_project.di.modules

import com.jesusbadenas.kotlin_clean_architecture_project.data.repositories.UserDataRepository
import com.jesusbadenas.kotlin_clean_architecture_project.di.PerActivity
import com.jesusbadenas.kotlin_clean_architecture_project.domain.common.UseCase
import com.jesusbadenas.kotlin_clean_architecture_project.domain.entities.UserEntity
import com.jesusbadenas.kotlin_clean_architecture_project.domain.interactors.GetUserDetails
import com.jesusbadenas.kotlin_clean_architecture_project.domain.interactors.GetUserList
import dagger.Module
import dagger.Provides

/**
 * Dagger module that provides user related collaborators.
 */
@Module
class UserModule {

    @Provides
    @PerActivity
    internal fun provideGetUserListUseCase(userDataRepository: UserDataRepository)
            : UseCase<List<UserEntity>> {
        return GetUserList(userDataRepository)
    }

    @Provides
    @PerActivity
    internal fun provideGetUserDetailsUseCase(userDataRepository: UserDataRepository)
            : UseCase<UserEntity> {
        return GetUserDetails(userDataRepository)
    }
}
