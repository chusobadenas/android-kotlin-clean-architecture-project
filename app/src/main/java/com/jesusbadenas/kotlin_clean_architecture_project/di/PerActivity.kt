package com.jesusbadenas.kotlin_clean_architecture_project.di

import javax.inject.Scope

/**
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the activity to be memorized in the
 * correct component.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity
