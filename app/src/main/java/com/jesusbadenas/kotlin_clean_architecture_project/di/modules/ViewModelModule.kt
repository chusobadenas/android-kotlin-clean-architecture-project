package com.jesusbadenas.kotlin_clean_architecture_project.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jesusbadenas.kotlin_clean_architecture_project.di.ViewModelKey
import com.jesusbadenas.kotlin_clean_architecture_project.domain.interactors.GetUserList
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserEntityMapper
import com.jesusbadenas.kotlin_clean_architecture_project.viewmodel.UserListViewModel
import com.jesusbadenas.kotlin_clean_architecture_project.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    internal fun provideUserListViewModel(
        getUserList: GetUserList, userEntityMapper: UserEntityMapper
    ): ViewModel {
        return UserListViewModel(getUserList, userEntityMapper)
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(map: Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModel>):
            ViewModelProvider.Factory {
        return ViewModelFactory(map)
    }
}
