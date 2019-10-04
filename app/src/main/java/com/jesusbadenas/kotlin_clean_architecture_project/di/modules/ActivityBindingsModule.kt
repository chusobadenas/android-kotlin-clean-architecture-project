package com.jesusbadenas.kotlin_clean_architecture_project.di.modules

import com.jesusbadenas.kotlin_clean_architecture_project.common.BaseActivity
import com.jesusbadenas.kotlin_clean_architecture_project.di.PerActivity
import com.jesusbadenas.kotlin_clean_architecture_project.userdetails.UserDetailsActivity
import com.jesusbadenas.kotlin_clean_architecture_project.userlist.MainActivity
import com.jesusbadenas.kotlin_clean_architecture_project.userlist.UserListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {

    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun contributeBaseActivityInjector(): BaseActivity

    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivityInjector(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [UserModule::class])
    internal abstract fun contributeUserListActivityInjector(): UserListActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [UserModule::class])
    internal abstract fun contributeUserDetailsActivityInjector(): UserDetailsActivity
}
