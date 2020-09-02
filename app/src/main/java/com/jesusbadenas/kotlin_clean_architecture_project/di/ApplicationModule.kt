package com.jesusbadenas.kotlin_clean_architecture_project.di

import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserDataMapper
import com.jesusbadenas.kotlin_clean_architecture_project.navigation.Navigator
import com.jesusbadenas.kotlin_clean_architecture_project.userdetails.UserDetailsFragment
import com.jesusbadenas.kotlin_clean_architecture_project.userlist.UserAdapter
import com.jesusbadenas.kotlin_clean_architecture_project.userlist.UserListFragment
import com.jesusbadenas.kotlin_clean_architecture_project.viewmodel.MainViewModel
import com.jesusbadenas.kotlin_clean_architecture_project.viewmodel.UserDetailsViewModel
import com.jesusbadenas.kotlin_clean_architecture_project.viewmodel.UserListViewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { UserAdapter() }
    factory { UserDataMapper() }
    fragment { UserListFragment() }
    fragment { UserDetailsFragment() }
    single { Navigator() }
    viewModel { MainViewModel() }
    viewModel { UserDetailsViewModel(get(), get()) }
    viewModel { UserListViewModel(get(), get()) }
}
