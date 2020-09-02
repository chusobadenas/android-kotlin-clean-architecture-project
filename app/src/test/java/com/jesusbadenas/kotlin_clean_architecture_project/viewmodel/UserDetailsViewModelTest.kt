package com.jesusbadenas.kotlin_clean_architecture_project.viewmodel

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jesusbadenas.kotlin_clean_architecture_project.data.entities.UserData
import com.jesusbadenas.kotlin_clean_architecture_project.domain.repositories.UserRepository
import com.jesusbadenas.kotlin_clean_architecture_project.entities.mappers.UserDataMapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserDetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    private lateinit var userRepository: UserRepository

    private lateinit var userDetailsVM: UserDetailsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userDetailsVM = UserDetailsViewModel(userRepository, UserDataMapper())
    }

    @Test
    fun testLoadUserDetailsSuccess() = runBlockingTest {
        val userData = mockk<UserData>()
        every { userData.userId } returns 1
        coEvery { userRepository.user(1) } returns userData

        userDetailsVM.loadUserDetails(1)

        assertEquals(userDetailsVM.retryVisibility.value, View.GONE)
        assertEquals(userDetailsVM.loadingVisibility.value, View.VISIBLE)
        assertEquals(userDetailsVM.user.value?.userId, 1)
    }
}
