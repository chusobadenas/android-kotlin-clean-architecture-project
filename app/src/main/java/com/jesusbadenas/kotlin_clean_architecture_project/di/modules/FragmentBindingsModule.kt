package com.jesusbadenas.kotlin_clean_architecture_project.di.modules

import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseFragment
import com.jesusbadenas.kotlin_clean_architecture_project.di.PerActivity
import com.jesusbadenas.kotlin_clean_architecture_project.userdetails.UserDetailsFragment
import com.jesusbadenas.kotlin_clean_architecture_project.userlist.UserListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingsModule {

    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun contributeBaseFragmentInjector(): BaseFragment

    @PerActivity
    @ContributesAndroidInjector(modules = [UserModule::class])
    internal abstract fun contributeUserListFragmentInjector(): UserListFragment

    @PerActivity
    @ContributesAndroidInjector(modules = [UserModule::class])
    internal abstract fun contributeUserDetailsFragmentInjector(): UserDetailsFragment
}
