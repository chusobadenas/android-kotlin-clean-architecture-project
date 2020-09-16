package com.jesusbadenas.kotlin_clean_architecture_project.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.jesusbadenas.kotlin_clean_architecture_project.App
import com.jesusbadenas.kotlin_clean_architecture_project.main.MainFragmentDirections
import com.jesusbadenas.kotlin_clean_architecture_project.userlist.UserListFragmentDirections
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = App::class, sdk = [27])
class NavigatorTest : AutoCloseKoinTest() {

    private val navigator = Navigator()

    @MockK(relaxed = true)
    lateinit var fragment: Fragment

    @MockK(relaxed = true)
    lateinit var navController: NavController

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { fragment.findNavController() } returns navController
    }

    @Test
    fun testNavigateToUserListSuccess() {
        val directions = MainFragmentDirections.navigateToUserListFragment()
        every { navController.navigate(directions) } just Runs

        navigator.navigateToUserList(fragment)

        verify { navController.navigate(directions) }
    }

    @Test
    fun testNavigateToUserDetailsSuccess() {
        val directions = UserListFragmentDirections.navigateToUserDetailsFragment(1)
        every { navController.navigate(directions) } just Runs

        navigator.navigateToUserDetails(fragment, 1)

        verify { navController.navigate(directions) }
    }
}
