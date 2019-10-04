package com.jesusbadenas.kotlin_clean_architecture_project

import com.jesusbadenas.kotlin_clean_architecture_project.di.components.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Android main Application
 */
class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
    }
}
