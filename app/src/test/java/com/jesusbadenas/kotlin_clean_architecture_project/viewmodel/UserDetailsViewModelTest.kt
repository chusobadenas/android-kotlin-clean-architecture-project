package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserDataMapper
import com.jesusbadenas.kotlin_clean_architecture_project.test.MainCoroutineRule
import com.jesusbadenas.kotlin_clean_architecture_project.test.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    val coroutineRule = MainCoroutineRule()

    @MockK
    lateinit var userRepository: UserRepository

    private lateinit var userDetailsVM: UserDetailsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userDetailsVM = UserDetailsViewModel(USER_ID, userRepository, UserDataMapper())
    }

    @Test
    fun testLoadUserDetailsSuccess() {
        val userData = UserData(USER_ID)
        every { userRepository.user(USER_ID) } returns MutableLiveData(userData)

        val user = userDetailsVM.user.getOrAwaitValue()

        assertNotNull(user)
        assertEquals(user.userId, USER_ID)
    }
}
