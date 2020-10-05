package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserDataMapper
import com.jesusbadenas.kotlin_clean_architecture_project.test.CoroutinesTestRule
import com.jesusbadenas.kotlin_clean_architecture_project.test.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserDetailsViewModelTest {

    companion object {
        private const val USER_ID = 1
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutinesTestRule()

    @MockK
    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testLoadUserDetailsError() = coroutineRule.runBlockingTest {
        val exception = Exception()
        coEvery { userRepository.user(USER_ID) } throws exception

        val userDetailsVM = UserDetailsViewModel(USER_ID, userRepository, UserDataMapper())
        val error = userDetailsVM.uiError.getOrAwaitValue()

        assertEquals(exception, error.throwable)
    }

    @Test
    fun testLoadUserDetailsSuccess() = coroutineRule.runBlockingTest {
        val userData = UserData(USER_ID)
        coEvery { userRepository.user(USER_ID) } returns userData

        val userDetailsVM = UserDetailsViewModel(USER_ID, userRepository, UserDataMapper())
        val user = userDetailsVM.user.getOrAwaitValue()

        assertNotNull(user)
        assertEquals(user.userId, USER_ID)
    }
}
