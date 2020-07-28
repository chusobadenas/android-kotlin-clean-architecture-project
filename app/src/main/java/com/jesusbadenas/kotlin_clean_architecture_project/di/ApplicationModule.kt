package com.jesusbadenas.kotlin_clean_architecture_project.di

import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserEntityMapper
import com.jesusbadenas.kotlin_clean_architecture_project.navigation.Navigator
import com.jesusbadenas.kotlin_clean_architecture_project.userdetails.UserDetailsFragment
import com.jesusbadenas.kotlin_clean_architecture_project.userlist.UserAdapter
import com.jesusbadenas.kotlin_clean_architecture_project.userlist.UserListFragment
import com.jesusbadenas.kotlin_clean_architecture_project.viewmodel.UserDetailsViewModel
import com.jesusbadenas.kotlin_clean_architecture_project.viewmodel.UserListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    factory { UserAdapter(androidContext()) }
    factory { UserEntityMapper() }
    fragment { UserListFragment() }
    fragment { UserDetailsFragment() }
    single { Navigator() }
    viewModel { UserDetailsViewModel(get(qualifier = named("getUserDetails")), get()) }
    viewModel { UserListViewModel(get(qualifier = named("getUserList")), get()) }
}
