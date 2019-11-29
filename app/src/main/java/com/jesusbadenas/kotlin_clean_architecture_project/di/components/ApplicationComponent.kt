package com.jesusbadenas.kotlin_clean_architecture_project.di.components

import android.content.Context
import com.jesusbadenas.kotlin_clean_architecture_project.App
import com.jesusbadenas.kotlin_clean_architecture_project.data.api.APIService
import com.jesusbadenas.kotlin_clean_architecture_project.data.repositories.UserDataRepository
import com.jesusbadenas.kotlin_clean_architecture_project.di.ApplicationContext
import com.jesusbadenas.kotlin_clean_architecture_project.di.modules.ActivityBindingsModule
import com.jesusbadenas.kotlin_clean_architecture_project.di.modules.ApplicationModule
import com.jesusbadenas.kotlin_clean_architecture_project.di.modules.FragmentBindingsModule
import com.jesusbadenas.kotlin_clean_architecture_project.di.modules.ViewModelModule
import com.jesusbadenas.kotlin_clean_architecture_project.navigation.Navigator
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBindingsModule::class,
        FragmentBindingsModule::class,
        ApplicationModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        fun build(): ApplicationComponent

        @BindsInstance
        fun application(application: App): Builder
    }

    @ApplicationContext
    fun context(): Context

    fun apiService(): APIService

    fun navigator(): Navigator

    fun userDataRepository(): UserDataRepository
}
