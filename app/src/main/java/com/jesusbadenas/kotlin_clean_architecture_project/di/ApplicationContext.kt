package com.jesusbadenas.kotlin_clean_architecture_project.di

import javax.inject.Qualifier

/**
 * A qualifier annotation to identify application context dependency
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext
