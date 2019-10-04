package com.jesusbadenas.kotlin_clean_architecture_project.di.components

import android.app.Activity
import com.jesusbadenas.kotlin_clean_architecture_project.di.PerActivity
import com.jesusbadenas.kotlin_clean_architecture_project.di.modules.ActivityModule
import dagger.Component

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 * [PerActivity]
 */
@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun activity(): Activity
}
