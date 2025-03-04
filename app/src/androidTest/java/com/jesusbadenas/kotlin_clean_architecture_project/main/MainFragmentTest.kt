package com.jesusbadenas.kotlin_clean_architecture_project.main

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jesusbadenas.kotlin_clean_architecture_project.data.di.dataModule
import com.jesusbadenas.kotlin_clean_architecture_project.domain.di.domainModule
import com.jesusbadenas.kotlin_clean_architecture_project.presentation.R
import com.jesusbadenas.kotlin_clean_architecture_project.presentation.di.presentationModule
import com.jesusbadenas.kotlin_clean_architecture_project.presentation.main.MainActivity
import com.jesusbadenas.kotlin_clean_architecture_project.test.CustomKoinJUnit4Test
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainFragmentTest: CustomKoinJUnit4Test(dataModule, domainModule, presentationModule)  {

    @Test
    fun testNavigateToListFragmentSuccess() {
        // Create activity
        ActivityScenario.launch(MainActivity::class.java).apply {
            moveToState(Lifecycle.State.RESUMED)
        }

        // Verify fragment is opened
        onView(withId(R.id.button_load_data)).perform(click())
        onView(withId(R.id.rv_users)).check(matches((isDisplayed())))
    }
}
