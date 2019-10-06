package com.jesusbadenas.kotlin_clean_architecture_project.navigation

import android.content.ActivityNotFoundException
import android.content.Context
import com.jesusbadenas.kotlin_clean_architecture_project.App
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = App::class, sdk = [27])
class NavigatorTest {

    private val navigator = Navigator()

    @MockK(relaxed = true)
    lateinit var context: Context

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testNavigateToUserListSuccess() {
        try {
            navigator.navigateToUserList(context)
        } catch (exception: ActivityNotFoundException) {
            fail("UserListActivity not found")
        }
    }

    @Test
    fun testNavigateToUserDetailsSuccess() {
        try {
            navigator.navigateToUserDetails(context, 1)
        } catch (exception: ActivityNotFoundException) {
            fail("UserDetailsActivity not found")
        }
    }
}
